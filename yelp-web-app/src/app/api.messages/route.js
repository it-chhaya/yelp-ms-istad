import { producer } from '@/lib/kafka';
import { NextResponse } from 'next/server';

export async function POST(request) {
    try {
        const message = await request.json();

        await producer.connect();
        await producer.send({
            topic: 'consumer-topic',
            messages: [
                {
                    key: message.id,
                    value: JSON.stringify(message)
                },
            ],
        });

        return NextResponse.json({ success: true });
    } catch (error) {
        console.error('Failed to send message:', error);
        return NextResponse.json(
            { error: 'Failed to send message' },
            { status: 500 }
        );
    } finally {
        await producer.disconnect();
    }
}
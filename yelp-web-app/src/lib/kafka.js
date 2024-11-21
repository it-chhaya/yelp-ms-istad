import { Kafka } from 'kafkajs';

const kafka = new Kafka({
    clientId: 'nextjs-client',
    brokers: ['localhost:29092'],
});

export const producer = kafka.producer();
export const consumer = kafka.consumer({ groupId: 'nextjs-group' });

// pages/api/messages.ts (if using Pages Router)
// or app/api/messages/route.ts (if using App Router)

// app/protected/page.tsx
import { serverFetch } from '@/lib/server-fetch'
import { redirect } from 'next/navigation'

export default async function Page() {
    return (
        <AuthenticatedDataWrapper>
            <ProtectedContent />
        </AuthenticatedDataWrapper>
    )
}
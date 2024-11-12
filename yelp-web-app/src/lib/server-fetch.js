// utils/server-fetch.ts
import { cookies } from 'next/headers'

export async function serverFetch(url, options) {
    const cookieStore = await cookies()
    const allCookies = cookieStore.getAll()

    // Convert cookies to header string format
    const cookieHeader = allCookies
        .map(cookie => `${cookie.name}=${cookie.value}`)
        .join('; ')

    const response = await fetch(`${process.env.GATEWAY_URL}${url}`, {
        ...options,
        credentials: 'include',
        headers: {
            ...options.headers,
            Cookie: cookieHeader,
            'Accept': 'application/json',
        },
    })

    if (response.status === 401 || response.status === 403) {
        // Handle unauthenticated state
        throw new Error('Unauthorized')
    }

    return response
}
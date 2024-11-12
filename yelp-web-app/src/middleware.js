import { NextResponse } from 'next/server'
import { NextRequest } from 'next/server'

export function middleware(request) {
    const requestHeaders = new Headers(request.headers)

    // Forward all cookies from the incoming request
    const forwardCookies = request.cookies.toString()
    if (forwardCookies) {
        requestHeaders.set('Cookie', forwardCookies)
    }

    return NextResponse.next({
        request: {
            headers: requestHeaders,
        },
    })
}

export const config = {
    matcher: '/api/:path*',
}
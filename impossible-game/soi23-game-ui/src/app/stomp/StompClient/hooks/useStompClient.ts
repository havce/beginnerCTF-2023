import { useCallback, useContext, useEffect, useRef } from 'react'
import Context from '../../Context'

export default function useStompClient({
    destination,
    onMessage,
}: {
    destination?: string,
    onMessage?(msg: { body: string }): void,
}) {
    const stompClient = useContext(Context)

    const onMessageRef = useRef<(msg: { body: string }) => void>()
    onMessageRef.current = onMessage

    const send = useCallback((body?: string) => {
        if (stompClient && destination !== undefined) {
            stompClient.publish({
                destination,
                body
            })
        }
    }, [stompClient, destination])

    useEffect(() => {
        let subscribedDestination: string | null = null
        if (stompClient && destination !== undefined && onMessageRef.current !== undefined) {
            subscribedDestination = destination
            stompClient.subscribe(destination, onMessageRef.current)
        }
        return () => {
            if (stompClient && subscribedDestination) {
                stompClient.unsubscribe(subscribedDestination)
            }
        }
    }, [stompClient, destination])

    return {
        send
    }
}

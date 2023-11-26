import React, { useCallback, useEffect, useRef, useState } from 'react'
import SockJS from 'sockjs-client'
import { Client, IFrame } from '@stomp/stompjs'
import Context from '../Context'
import { StompClient } from 'src/app/utils/interfaces'

const BROKER_URL = '/api/websocket'

export default function StompProvider({
    children
}: {
    children: React.ReactNode
}) {
    const [contextValue, setContextValue] = useState<StompClient | null>(null)

    const onConnect = useCallback(() => {
        console.log('Connected')
        setContextValue(stompClientRef.current)
    }, [])

    const onDisconnect = useCallback(() => {
        console.log('Disconnected')
        setContextValue(null)
    }, [])

    const onStompError = useCallback((frame: IFrame) => {
        console.error('Broker reported error: ' + frame.headers['message'])
        console.error('Additional details: ' + frame.body)
    }, [])

    const onWebSocketError = useCallback((error: unknown) => {
        console.error('Error with websocket:', error)
    }, [])

    const stompClientRef = useRef(new Client({
        logRawCommunication: true,
        webSocketFactory: () => new SockJS(BROKER_URL),
        onConnect,
        onDisconnect,
        onStompError,
        onWebSocketError,
    }))

    useEffect(() => {
        const stompClient = stompClientRef.current
        stompClient.activate()
        return () => {
            stompClient.deactivate()
        }
    }, [])

    return (
        <Context.Provider value={contextValue} >
            {children}
        </Context.Provider>
    )
}

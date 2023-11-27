import { createContext } from 'react'
import { StompClient } from '../utils/interfaces'

export default createContext<StompClient | null>(null)

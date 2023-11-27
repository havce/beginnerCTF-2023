import { SnackbarProvider } from 'notistack'
import '@fontsource/public-sans'
import {
    CssVarsProvider,
    Stack,
    Typography
} from '@mui/joy'
import StompProvider from './stomp/StompProvider'
import theme from './theme'
import PlayField from './PlayField'

export default function App() {
    return (
        <StompProvider>
            <SnackbarProvider preventDuplicate>
                <CssVarsProvider theme={theme}>
                    <Stack
                        spacing={2}
                        alignItems='center'
                        justifyContent='center'
                    >
                        <Stack
                            spacing={2}
                            alignItems='center'
                            justifyContent='center'
                            direction='row'
                        >
                            <Typography
                                noWrap
                                level='display1'
                                variant='soft'
                                color='primary'
                            >
                                Impossible Game
                            </Typography>
                            <Stack
                                alignItems='left'
                                justifyContent='center'
                            >
                                <Typography
                                    noWrap
                                    level='h6'
                                    variant='soft'
                                    color='primary'
                                >
                                    Up: W
                                </Typography>
                                <Typography
                                    noWrap
                                    level='h6'
                                    variant='soft'
                                    color='primary'
                                >
                                    Down: S
                                </Typography>
                                <Typography
                                    noWrap
                                    level='h6'
                                    variant='soft'
                                    color='primary'
                                >
                                    Start: Space
                                </Typography>

                            </Stack>
                        </Stack>

                        <PlayField />
                    </Stack>
                </CssVarsProvider>
            </SnackbarProvider>
        </StompProvider>
    )
}

package main

import (
	"context"
	"crypto/rand"
	"encoding/hex"
	"errors"
	"fmt"
	"log"
	"net"
	"os"
	"os/signal"
	"sync"
	"time"
)

type Server struct {
	ln net.Listener

	quit chan struct{}
	wg   sync.WaitGroup
}

func NewServer(ln net.Listener) *Server {
	return &Server{ln: ln, quit: make(chan struct{})}
}

func (s *Server) Serve() {
	s.wg.Add(1)
	go s.serve()
}

func Run(ctx context.Context) error {
	ln, err := net.Listen("tcp4", ":1337")
	if err != nil {
		return err
	}

	s := NewServer(ln)
	s.Serve()
	fmt.Println("Listening on :1337")

	<-ctx.Done()
	s.Close()
	return nil
}

func (s *Server) Close() {
	close(s.quit)
	s.ln.Close()
	s.wg.Wait()
}

func (s *Server) serve() {
	defer s.wg.Done()

	for {
		conn, err := s.ln.Accept()
		if err != nil {
			select {
			case <-s.quit:
				return
			default:
				log.Println("accept error", err)
			}
		} else {
			s.wg.Add(1)
			conn.SetDeadline(time.Now().Add(time.Second * 30))
			go func() {
				if err := s.handleConnection(conn); errors.Is(err, os.ErrDeadlineExceeded) {
					conn.SetDeadline(time.Now().Add(time.Second * 3))
					if _, err := fmt.Fprintln(conn, "Time's up! Please retry another time."); err != nil {
						log.Println(err)
					}
				} else if err != nil {
					log.Println(err)
				}
				conn.Close()
				s.wg.Done()
			}()
		}
	}
}

func (s *Server) handleConnection(c net.Conn) error {
	_, err := fmt.Fprintln(c, `
 ██████╗██████╗ ██╗   ██╗████████╗██████╗  ██████╗  ██████╗ ██████╗  █████╗ ███████╗██╗   ██╗
██╔════╝██╔══██╗╚██╗ ██╔╝╚══██╔══╝██╔══██╗██╔═══██╗██╔════╝ ██╔══██╗██╔══██╗██╔════╝╚██╗ ██╔╝
██║     ██████╔╝ ╚████╔╝    ██║   ██████╔╝██║   ██║██║  ███╗██████╔╝███████║█████╗   ╚████╔╝ 
██║     ██╔══██╗  ╚██╔╝     ██║   ██╔═══╝ ██║   ██║██║   ██║██╔══██╗██╔══██║██╔══╝    ╚██╔╝  
╚██████╗██║  ██║   ██║      ██║   ██║     ╚██████╔╝╚██████╔╝██║  ██║██║  ██║██║        ██║   
 ╚═════╝╚═╝  ╚═╝   ╚═╝      ╚═╝   ╚═╝      ╚═════╝  ╚═════╝ ╚═╝  ╚═╝╚═╝  ╚═╝╚═╝        ╚═╝   `)
	if err != nil {
		return err
	}

	_, err = fmt.Fprintln(c, `Hello crytpografy enthusiast! Welcome to the havce cryptosystem.
Let's start! We're going to send you the flag encrypted (XORed) with a random key.
Then you can try our crypto system and encrypt your messages too with our secret key! c:`)
	if err != nil {
		return err
	}

	flag := os.Getenv("FLAG")
	if len(flag) > 32 {
		flag = flag[:32]
	}

	var key [32]byte

	_, err = rand.Read(key[:])
	if err != nil {
		return err
	}

	fmt.Fprintln(c, "[*] Generated the 32 byte secret key!")

	var out [32]byte
	for i := 0; i < min(len(out), len(flag)); i++ {
		out[i] = flag[i] ^ key[i]
	}

	fmt.Fprintln(c, "[*] Encrytped the flag (byte-to-byte XORed with the secret key)")
	if _, err := fmt.Fprintf(c, "The encrytped flag: %s\n", hex.EncodeToString(out[:])); err != nil {
		return err
	}

	if _, err := fmt.Fprintf(c, "Please paste here your hex-encoded message (max 32 byte): "); err != nil {
		return err
	}

	str := ""
	_, err = fmt.Fscanf(c, "%s", &str)
	if err != nil {
		return err
	}

	n := int(len(str) / 2)

	msg, err := hex.DecodeString(str)
	if err != nil {
		return err
	}

	if len(msg) > 32 {
		msg = msg[:32]
	}

	for i := 0; i < min(n, 32); i++ {
		out[i] = msg[i] ^ key[i]
	}

	if _, err := fmt.Fprintf(c, "Your encrytped message (byte-to-byte XORed with the secret key): %s\n", hex.EncodeToString(out[:])); err != nil {
		return err
	}

	return nil
}

func main() {
	ctx, _ := signal.NotifyContext(context.Background(), os.Interrupt)

	if err := Run(ctx); err != nil {
		fmt.Fprintf(os.Stderr, "error: %v", err)
		os.Exit(1)
	}
}

# Chatty Chat Chat: A Simple Java Chat Application
A Java-based chat application allowing concurrent server-client interactions using the ChattyChatChat protocol.

## Table of Contents

- [Introduction](#introduction)
- [Features](#features)
- [Running the Application](#running-the-application)
  - [Server](#server)
  - [Client](#client)
- [Protocol Commands](#protocol-commands)
- [Implementation Details](#implementation-details)
  - [`String` Member Method](#string-member-method)
  - [Hostnames and Ports](#hostnames-and-ports)
- [Additional Information](#additional-information)

## Introduction

Modern computing capabilities enable simultaneous multi-tasking. For instance, one might be browsing a webpage, while in the background, emails are being fetched or music is being played. This concurrent execution is vital for today's computing needs and has been integral to Java since its inception.

Concurrent execution becomes inevitable in networked applications where asynchronous communication between processes is essential.

## Features

- Establish server-client connections using Java sockets.
- Manage concurrent tasks with Java threads.
- Use the custom ChattyChatChat (CCC) protocol to mediate client interactions.

## Running the Application

### Server

To start the chat server, invoke the `ChattyChatChatServer` class and specify the port for the server to listen on.

Example:
```
$ java ChattyChatChatServer 9876
```

### Client

To run the chat client, invoke the `ChattyChatChatClient` class, specifying the server's hostname and the port it's listening on.

Example:
```
$ java ChattyChatChatClient localhost 9876
```

Ensure the server is operational before attempting a connection.

## Protocol Commands

The ChattyChatChat protocol provides a single "chat room" for all connected clients. Standard messages are broadcasted to all clients. Special server behaviors can be requested via specific commands:

- `/nick <name>`: Sets the client's nickname.
- `/dm <nickname> <message>`: Sends a direct message to specified user(s).
- `/quit`: Disconnects the client from the server.

## Implementation Details

### `String` Member Method

The `String.split(String, int)` method from the `java.lang.String` class can be used to split strings based on delimiters. This can be handy for parsing command inputs.

Example:
```
String msg = "/dm re268 hello world";
String[] words = msg.split(" ", 3);
```

### Hostnames and Ports

For local testing, `localhost` resolves to the IP address `127.0.0.1`, referring to the current machine. Depending on your IDE, there are ways to specify command-line arguments for testing.

## Additional Information

Early text-based messaging has roots in builtin board servers (BBS) and Usenet protocols. The real-time chat protocol [Internet Relay Chat (IRC)](https://en.wikipedia.org/wiki/Internet_Relay_Chat) became widely used since its introduction in 1988.

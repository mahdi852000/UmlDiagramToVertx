package com;

import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.MessageCodec;

public class InstructionCommandCodec implements MessageCodec<InstructionCommand,InstructionCommand> {
    @Override
    public void encodeToWire(Buffer buffer, InstructionCommand command) {
        buffer.appendString(command.getCommand());
    }

    @Override
    public InstructionCommand decodeFromWire(int pos, Buffer buffer) {
        String cmd = buffer.getString(pos, buffer.length());
        return new InstructionCommand(cmd);
    }

    @Override
    public InstructionCommand transform(InstructionCommand command) {
        return command;
    }

    @Override
    public String name() {
        return "InstructionCommandCodec";
    }

    @Override
    public byte systemCodecID() {
        return -1;
    }
}

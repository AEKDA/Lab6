package core.logic;

import core.net.NetManager;
import java.net.InetAddress;

public interface Client {

    void setInstruction(InstructionInfo[] instructionList);

    void stop();

    InstructionListener getListener();

    NetManager getNetManager();

    int getServerPort();

    InetAddress getServerHost();

    void switchState(ClinetState state);

}

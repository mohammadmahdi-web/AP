import java.io.*;

public class TempObjectOutputStream extends ObjectOutputStream {
    public TempObjectOutputStream(OutputStream out) throws IOException {
        super(out);
    }
    @Override
    protected void writeStreamHeader() throws IOException {
        reset();
    }
}

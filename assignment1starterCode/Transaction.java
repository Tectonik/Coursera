import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;

public class Transaction
{

    public class Input
    {
        /**
         * hash of the Transaction whose output is being used
         */

        public byte[] previousTransactionHash;
        /**
         * used output's index in the previous transaction
         */

        public int outputIndex;
        /**
         * the signature produced to check valid
         */

        public byte[] signature;

        public Input(byte[] prevHash, int index)
        {
            if (prevHash == null)
            {
                previousTransactionHash = null;
            }
            else
            {
                previousTransactionHash = Arrays.copyOf(prevHash, prevHash.length);
            }
            outputIndex = index;
        }

        public void addSignature(byte[] signature)
        {
            if (signature == null)
            {
                this.signature = null;
            }
            else
            {
                this.signature = Arrays.copyOf(signature, signature.length);
            }
        }
    }

    public class Output
    {
        /**
         * value in bitcoins of the output
         */
        public double value;

        /**
         * the address or public key of the recipient
         */
        public PublicKey address;

        public Output(double value, PublicKey address)
        {
            this.value = value;
            this.address = address;
        }
    }

    /**
     * hash of the transaction, its unique id
     */
    private byte[] hash;

    private ArrayList<Input> inputs;

    private ArrayList<Output> outputs;

    public Transaction()
    {
        inputs = new ArrayList<Input>();
        outputs = new ArrayList<Output>();
    }

    public Transaction(Transaction transaction)
    {
        hash = transaction.hash.clone();
        inputs = new ArrayList<Input>(transaction.inputs);
        outputs = new ArrayList<Output>(transaction.outputs);
    }

    public void addInput(byte[] previousTransactionHash, int outputIndex)
    {
        Input in = new Input(previousTransactionHash, outputIndex);
        inputs.add(in);
    }

    public void addOutput(double value, PublicKey address)
    {
        Output op = new Output(value, address);
        outputs.add(op);
    }

    public void removeInput(int index)
    {
        inputs.remove(index);
    }

    public void removeInput(UnspentTransactionOutput unspentTransactionOutput)
    {
        for (int current = 0; current < inputs.size(); current++)
        {
            Input input = inputs.get(current);
            UnspentTransactionOutput u = new UnspentTransactionOutput(input.previousTransactionHash, input.outputIndex);
            if (u.equals(unspentTransactionOutput))
            {
                inputs.remove(current);
                return;
            }
        }
    }

    public byte[] getRawDataToSign(int index)
    {
        // ith input and all outputs
        ArrayList<Byte> signatureData = new ArrayList<Byte>();
        if (index > inputs.size())
        {
            return null;
        }

        Input input = inputs.get(index);
        byte[] previousTransactionHash = input.previousTransactionHash;
        ByteBuffer buffer = ByteBuffer.allocate(Integer.SIZE / 8);
        buffer.putInt(input.outputIndex);
        byte[] outputIndex = buffer.array();

        if (previousTransactionHash != null)
        {
            for (int current = 0; current < previousTransactionHash.length; current++)
                signatureData.add(previousTransactionHash[current]);
        }
        for (int current = 0; current < outputIndex.length; current++)
            signatureData.add(outputIndex[current]);
        for (Output output : outputs)
        {
            ByteBuffer bo = ByteBuffer.allocate(Double.SIZE / 8);
            bo.putDouble(output.value);
            byte[] value = bo.array();
            byte[] addressBytes = output.address.getEncoded();
            for (int i = 0; i < value.length; i++)
                signatureData.add(value[i]);

            for (int i = 0; i < addressBytes.length; i++)
                signatureData.add(addressBytes[i]);
        }
        byte[] signatureD = new byte[signatureData.size()];
        int current = 0;
        for (Byte sb : signatureData)
        {
            signatureD[current++] = sb;
        }

        return signatureD;
    }

    public void addSignature(byte[] signature, int index)
    {
        inputs.get(index).addSignature(signature);
    }

    public byte[] getRawTransaction()
    {
        ArrayList<Byte> rawTransaction = new ArrayList<Byte>();
        for (Input input : inputs)
        {
            byte[] previousTransactionHash = input.previousTransactionHash;
            ByteBuffer buffer = ByteBuffer.allocate(Integer.SIZE / 8);
            buffer.putInt(input.outputIndex);
            byte[] outputIndex = buffer.array();
            byte[] signature = input.signature;

            if (previousTransactionHash != null)
            {
                for (int current = 0; current < previousTransactionHash.length; current++)
                    rawTransaction.add(previousTransactionHash[current]);
            }

            for (int current = 0; current < outputIndex.length; current++)
            {
                rawTransaction.add(outputIndex[current]);
            }

            if (signature != null)
            {
                for (int current = 0; current < signature.length; current++)
                {
                    rawTransaction.add(signature[current]);
                }
            }
        }
        for (Output output : outputs)
        {
            ByteBuffer buffer = ByteBuffer.allocate(Double.SIZE / 8);
            buffer.putDouble(output.value);
            byte[] value = buffer.array();
            byte[] addressBytes = output.address.getEncoded();

            for (int current = 0; current < value.length; current++)
            {
                rawTransaction.add(value[current]);
            }

            for (int current = 0; current < addressBytes.length; current++)
            {
                rawTransaction.add(addressBytes[current]);
            }

        }
        byte[] transaction = new byte[rawTransaction.size()];
        int current = 0;
        for (Byte b : rawTransaction)
        {
            transaction[current++] = b;
        }

        return transaction;
    }

    public void finalize()
    {
        try
        {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(getRawTransaction());
            hash = messageDigest.digest();
        }
        catch (NoSuchAlgorithmException exception)
        {
            exception.printStackTrace(System.err);
        }
    }

    public void setHash(byte[] hash)
    {
        this.hash = hash;
    }

    public byte[] getHash()
    {
        return hash;
    }

    public ArrayList<Input> getInputs()
    {
        return inputs;
    }

    public ArrayList<Output> getOutputs()
    {
        return outputs;
    }

    public Input getInput(int index)
    {
        if (index < inputs.size())
        {
            return inputs.get(index);
        }
        return null;
    }

    public Output getOutput(int index)
    {
        if (index < outputs.size())
        {
            return outputs.get(index);
        }
        return null;
    }

    public int numInputs()
    {
        return inputs.size();
    }

    public int numOutputs()
    {
        return outputs.size();
    }
}

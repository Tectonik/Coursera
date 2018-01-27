import java.util.Arrays;

public class UnspentTransactionOutput implements Comparable<UnspentTransactionOutput>
{

    /**
     * Hash of the transaction from which this UnspentTransactionOutput originates
     */
    private byte[] transactionHash;

    /**
     * Index of the corresponding output in said transaction
     */
    private int index;

    /**
     * Creates a new UnspentTransactionOutput corresponding to the output with index <index> in the transaction whose
     * hash is {@code transactionHash}
     */
    public UnspentTransactionOutput(byte[] transactionHash, int index)
    {
        this.transactionHash = Arrays.copyOf(transactionHash, transactionHash.length);
        this.index = index;
    }

    /**
     * @return the transaction hash of this UnspentTransactionOutput
     */
    public byte[] getTransactionHash()
    {
        return transactionHash;
    }

    /**
     * @return the index of this UnspentTransactionOutput
     */
    public int getIndex()
    {
        return index;
    }

    /**
     * Compares this UnspentTransactionOutput to the one specified by {@code other}, considering them equal if they have
     * {@code transactionHash} arrays with equal contents and equal {@code index} values
     */
    public boolean equals(Object other)
    {
        if (other == null)
        {
            return false;
        }
        if (getClass() != other.getClass())
        {
            return false;
        }

        UnspentTransactionOutput unspentTransactionOutput = (UnspentTransactionOutput) other;
        byte[] hash = unspentTransactionOutput.txHash;
        int in = unspentTransactionOutput.index;
        if (hash.length != transactionHash.length || index != in)
        {
            return false;
        }
        for (int i = 0; i < hash.length; i++)
        {
            if (hash[i] != transactionHash[i])
            {
                return false;
            }
        }
        return true;
    }

    /**
     * Simple implementation of a UnspentTransactionOutput hashCode that respects equality of UnspentTransactionOutputs // (i.e.
     * UnspentTransactionOutput1.equals(UnspentTransactionOutput2) => UnspentTransactionOutput1.hashCode() == UnspentTransactionOutput2.hashCode())
     */
    public int hashCode()
    {
        int hash = 1;
        hash = hash * 17 + index;
        hash = hash * 31 + Arrays.hashCode(transactionHash);
        return hash;
    }

    /**
     * Compares this UnspentTransactionOutput to the one specified by {@code unspentTransactionOutput}
     */
    public int compareTo(UnspentTransactionOutput unspentTransactionOutput)
    {
        byte[] hash = unspentTransactionOutput.txHash;
        int in = unspentTransactionOutput.index;
        if (in > index)
        {
            return -1;
        }
        else if (in < index)
        {
            return 1;
        }
        else
        {
            int len1 = transactionHash.length;
            int len2 = hash.length;
            if (len2 > len1)
            {
                return -1;
            }
            else if (len2 < len1)
            {
                return 1;
            }
            else
            {
                for (int i = 0; i < len1; i++)
                {
                    if (hash[i] > transactionHash[i])
                    {
                        return -1;
                    }
                    else if (hash[i] < transactionHash[i])
                    {
                        return 1;
                    }
                }
                return 0;
            }
        }
    }
}

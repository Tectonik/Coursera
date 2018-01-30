import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class UnspentTransactionOutputPool
{

    /**
     * The current collection of UnspentTransactionOutputs, with each one mapped to its corresponding transaction output
     */
    private HashMap<UnspentTransactionOutput, Transaction.Output> unspentTransactionOutputOutputHashMap;

    /**
     * Creates a new empty UnspentTransactionOutputPool
     */
    public UnspentTransactionOutputPool()
    {
        unspentTransactionOutputOutputHashMap = new HashMap<UnspentTransactionOutput, Transaction.Output>();
    }

    /**
     * Creates a new UnspentTransactionOutputPool that is a copy of {@code uPool}
     */
    public UnspentTransactionOutputPool(UnspentTransactionOutputPool uPool)
    {
        unspentTransactionOutputOutputHashMap = new HashMap<UnspentTransactionOutput, Transaction.Output>(uPool.unspentTransactionOutputOutputHashMap);
    }

    /**
     * Adds a mapping from UnspentTransactionOutput {@code UnspentTransactionOutput} to transaction output @code{txOut} to the pool
     */
    public void addUnspentTransactionOutput(UnspentTransactionOutput UnspentTransactionOutput, Transaction.Output txOut)
    {
        unspentTransactionOutputOutputHashMap.put(UnspentTransactionOutput, txOut);
    }

    /**
     * Removes the UnspentTransactionOutput {@code UnspentTransactionOutput} from the pool
     */
    public void removeUnspentTransactionOutput(UnspentTransactionOutput UnspentTransactionOutput)
    {
        unspentTransactionOutputOutputHashMap.remove(UnspentTransactionOutput);
    }

    /**
     * @return the transaction output corresponding to UnspentTransactionOutput {@code UnspentTransactionOutput}, or null if {@code UnspentTransactionOutput} is
     * not in the pool.
     */
    public Transaction.Output getTxOutput(UnspentTransactionOutput unspentTransactionOutput)
    {
        return unspentTransactionOutputOutputHashMap.get(unspentTransactionOutput);
    }

    /**
     * @return true if UnspentTransactionOutput {@code UnspentTransactionOutput} is in the pool and false otherwise
     */
    public boolean contains(UnspentTransactionOutput UnspentTransactionOutput)
    {
        return unspentTransactionOutputOutputHashMap.containsKey(UnspentTransactionOutput);
    }

    /**
     * Returns an {@code ArrayList} of all UnspentTransactionOutputs in the pool
     */
    public ArrayList<UnspentTransactionOutput> getAllUnspentTransactionOutput()
    {
        Set<UnspentTransactionOutput> setUnspentTransactionOutput = unspentTransactionOutputOutputHashMap.keySet();
        ArrayList<UnspentTransactionOutput> allUnspentTransactionOutput = new ArrayList<UnspentTransactionOutput>();

        for (UnspentTransactionOutput unspentTransactionOutput : setUnspentTransactionOutput)
        {
            allUnspentTransactionOutput.add(unspentTransactionOutput);
        }

        return allUnspentTransactionOutput;
    }
}

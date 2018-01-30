import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;

public class TxHandler
{

    /**
     * Creates a public ledger whose current UTXOPool (collection of unspent transaction outputs) is
     * {@code utxoPool}. This should make a copy of utxoPool by using the UTXOPool(UTXOPool uPool)
     * constructor.
     */
    public TxHandler(UTXOPool utxoPool)
    {
        this.utxoPool = new UTXOPool(utxoPool);
    }

    public UTXOPool utxoPool;

    /**
     * @return true if:
     * (1) all outputs claimed by {@code tx} are in the current UTXO pool,
     * (2) the signatures on each input of {@code tx} are valid,
     * (3) no UTXO is claimed multiple times by {@code tx},
     * (4) all of {@code tx}s output values are non-negative, and
     * (5) the sum of {@code tx}s input values is greater than or equal to the sum of its output
     * values; and false otherwise.
     */
    public boolean isValidTx(Transaction tx)
    {

        Crypto cryptography = new Crypto();

        ArrayList<Transaction.Output> transactionOutputs = tx.getOutputs();
        // HashSet<Transaction.UTXO> outputVerifier = new HashSet<Transaction.UTXO>();

        int inputValues = 0;
        int outputValues = 0;

        int numberOfInputs = tx.numInputs()
        for (int current = 0; current < numberOfInputs; current++))
        {
            Transaction.input currentInput = tx.getInput(current);
                        
        }

        for (Transaction.Output output : transactionOutputs)
        {

            // if ()
        }

        boolean alloutputsClaimedAreInCurrentUTXOPool = true;
        boolean allSignaturesAreValid = true;
        boolean allUTXOsAreClaimedOnce = true;
        boolean allInputsArePositive = true;
        boolean inputsDoNotExceedOutputs = true;

        boolean transactionIsValid = alloutputsClaimedAreInCurrentUTXOPool 
                                    && allSignaturesAreValid 
                                    && allUTXOsAreClaimedOnce
                                    && allInputsArePositive
                                    && inputsDoNotExceedOutputs;
        return transactionIsValid;
    }

    /**
     * Handles each epoch by receiving an unordered array of proposed transactions, checking each
     * transaction for correctness, returning a mutually valid array of accepted transactions, and
     * updating the current UTXO pool as appropriate.
     */
    public Transaction[] handleTxs(Transaction[] possibleTxs)
    {

        // Transaction[] validTransactions = new Transaction[possibleTxs.length];
        ArrayList<Transaction> validTransactions = new ArrayList<Transaction>();

        for (Transaction transactionToCheck : possibleTxs)
        {

            boolean transactionIsValid = isValidTx(transactionToCheck);
            if (transactionIsValid)
            {
                validTransactions.add(transactionToCheck);
            }
        }

        // Update current UTXOPool

        return validTransactions;
    }

}

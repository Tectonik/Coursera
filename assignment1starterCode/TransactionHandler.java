public class transactionHandler
{

    /**
     * Creates a public ledger whose current UnspentTransactionOutputPool (collection of unspent transaction outputs) is
     * {@code unspentTransactionOutputPool}. This should make a copy of unspentTransactionOutputPool by using the UnspentTransactionOutputPool(UnspentTransactionOutputPool uPool)
     * constructor.
     */
    public transactionHandler(UnspentTransactionOutputPool unspentTransactionOutputPool)
    {
        // IMPLEMENT THIS
        // Wat?
    }

    /**
     * @return true if:
     * (1) all outputs claimed by {@code transaction} are in the current UnspentTransactionOutput pool,
     * (2) the signatures on each input of {@code transaction} are valid,
     * (3) no UnspentTransactionOutput is claimed multiple times by {@code transaction},
     * (4) all of {@code transaction}s output values are non-negative, and
     * (5) the sum of {@code transaction}s input values is greater than or equal to the sum of its output
     * values; and false otherwise.
     */
    public boolean isValidTransaction(Transaction transaction)
    {
        // IMPLEMENT THIS
        // Check keys?
    }

    /**
     * Handles each epoch by receiving an unordered array of proposed transactions, checking each
     * transaction for correctness, returning a mutually valid array of accepted transactions, and
     * updating the current UnspentTransactionOutput pool as appropriate.
     */
    public Transaction[] handleTransactions(Transaction[] possibleTtransactionss)
    {
        // IMPLEMENT THIS
    }

}

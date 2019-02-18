class TransactionActivity : AppCompatActivity() {

	private lateinit var database: FirebaseFirestore

	override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transtaction)

        database = FirebaseFirestore.getInstance()

        //some buttons click listeners
    }

	private fun makeTransaction() {
	    val reference = database.collection("club").document("acmilan")

	    database.runTransaction { transaction ->
	        val snapshot = transaction.get(reference)
	        val budget = snapshot.getLong("budget")
	        if(budget!! <= 1000)
	            transaction.update(reference, "budget", budget + 500L)
	    }
	}

	private fun makeWriteBatch() {
	    val batch = database.batch()

	    //add new document
	    val realMadrid = Club("Real Madrid", "Spain", 1902)
	    val rmReference = database.collection("club").document("realmadrid")
	    batch.set(rmReference, realMadrid)

	    //update current
	    val acmReference = database.collection("club").document("acmilan")
	    batch.update(acmReference, "budget", 2000L)

	    //do more add, update, delete operations

	    //commit operations and add some listeners
	    batch.commit().addOnCompleteListener {
	        //some action
	    }
	}
}
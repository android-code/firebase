class ReadActivity : AppCompatActivity() {

	private lateinit var database: FirebaseFirestore

	override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_read)

        database = FirebaseFirestore.getInstance()

        //some buttons click listeners
    }

	private fun getDocument() {
	    val reference = database.collection("club").document("acmilan")

	    //add optional source and listeners and retrieve document
	    val source = Source.SERVER //change to Source.CACHE for cached data
	    reference.get(source)
	        .addOnSuccessListener { document ->
	            //some action on DocumentSnapshot instance
	            val club = document.toObject(Club::class.java)
	        }
	        .addOnFailureListener { exception ->
	            //some action 
	        }
	}

	private fun getMultipleDocuments() {
	    //add some listeners and optional query clausule
	    database.collection("club").whereEqualTo("country", "Italy").get()
	        .addOnSuccessListener { documents ->
	            //some action on QueryDocumentSnapshot instance
	            for(document in documents) { }
	        }
	}

	private fun getDocumentsByQueries() {
	    //add queries and listeners
	    database.collection("club")
	        .whereEqualTo("country", "Italy")
	        .whereLessThan("year", 1930)
	        .orderBy("year", Query.Direction.DESCENDING)
	        .limit(5)
	        .get()
	        .addOnSuccessListener { documents ->
	            //some action on QueryDocumentSnapshot instance
	        }
	}

	private fun listenForChanges() {
	    val documentRef = database.collection("club").document("acmilan")
	    documentRef.addSnapshotListener(EventListener<DocumentSnapshot> { snapshot, exception ->
	        //check is exception
	        if (exception != null) return@EventListener

	        //check source of the changes
	        if (snapshot != null && snapshot.metadata.hasPendingWrites()) {
	            //local changes
	        }
	        else {
	            //server changes
	        }

	        //do more job
	    })

	    val collectionRef = database.collection("club")
	    collectionRef.addSnapshotListener(EventListener<QuerySnapshot> { snapshots, exception ->        
	        //check type of the change
	        for (changes in snapshots!!.documentChanges) {
	            //could be: DocumentChange.Type.ADDED, MODIFIED or REMOVED
	        }

	        //do more job
	    })
	}
}
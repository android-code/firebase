class WriteActivity : AppCompatActivity() {

	private lateinit var database: FirebaseFirestore

	override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_write)

        database = FirebaseFirestore.getInstance()

        //some buttons click listeners
    }

    private fun addDataWithId() {
	    //create data
	    var club = Club("Milan", "Italy", 1899)
	    club.uclTrophiesYear = listOf(1963, 1993, 1989, 1990, 1994, 2003)
	    club.budget = 1000L

	    //put data to database
	    database.collection("club").document("acmilan").set(club)
	        .addOnSuccessListener {
	            //some action
	        }
	        .addOnFailureListener {
	            //some action
	        }

	    //or use reference.set(club, SetOptions.merge()) for merge if file exists
	}

	private fun addDataWithoutId() {
	    var club = Club("Chelsea FC", "England", 1905)
	    database.collection("club").add(club)
	        .addOnSuccessListener { 
	            //some action 
	        }
	        .addOnFailureListener { 
	            //some action 
	        }
	}

	private fun updateData() {
	    val reference = database.collection("club").document("acmilan")
	    reference.update("name", "AC Milan")
	    //add some listeners
	}

	private fun updateArrayData() {
	    //add new data
	    val reference = database.collection("club").document("acmilan")
	    reference.update("uclTrophiesYear", FieldValue.arrayUnion(2007))
	    //add some listeners
	}

	private fun updateData() {
	    val reference = database.collection("club").document("acmilan")
	    reference.update("name", "AC Milan")
	    //add some listeners
	}

	private fun updateArrayData() {
	    //add new data
	    val reference = database.collection("club").document("acmilan")
	    reference.update("uclTrophiesYear", FieldValue.arrayUnion(2007))
	    //add some listeners
	}

	private fun deleteDocument() {
	    database.collection("club").document("acmilan").delete()
	    //add some listeners
	}

	private fun deleteField() {
	    val reference = database.collection("club").document("acmilan")

	    val updates = HashMap<String, Any>()
	    updates["uclTrophiesYear"] = FieldValue.delete()

	    reference.update(updates)
	    //add some listeners
	}
}
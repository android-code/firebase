class TypesActivity : AppCompatActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_types)

        //some buttons click listeners
    }

    private fun createReferences() {
	    val database = FirebaseFirestore.getInstance()
	    val collectionRef = database.collection("collection")
	    val documentRef = collectionRef.document("document")
	    val documentRef2 = database.document("collection/document") //or use direct path instead
	    val nestedDocumentRef = documentRef.collection("subcollection").document("nested")
	}

    private fun createExampleData() {
	    val document = HashMap<String, Any?>()
	    document["text"] = "value"
	    document["logic"] = true
	    document["number"] = 9.99
	    document["date"] = Timestamp(Date())
	    document["list"] = arrayListOf(1,2,3)
	    document["null"] = null
	}

	private fun createSimilarData() {
	    val person = HashMap<String, Any>()
	    person["name"] = "John"
	    person["surname"] = "Walker"
	    person["born"] = 1805

	    //structure of data in the same collection don't have to be exactly the same
	    val person2 = HashMap<String, Any>()
	    person2["name"] = "Jasper"
	    person2["second"] = "Newton"
	    person2["surname"] = "Daniel"
	    person2["born"] = 1850

	    //add to database by passing those objects
	}

	private fun createDataByObject() {
	    val person = Person("William", "Grant", 1839)
	    //add to database by passing object
	}
}
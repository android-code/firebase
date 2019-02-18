class LifecycleActivity : AppCompatActivity() {

	private var fileRef: StorageReference?

	override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lifecycle)

        //start some upload task on fileRef
    }

    override fun onSaveInstanceState(outState: Bundle) {
	    super.onSaveInstanceState(outState)
	    //save the reference to upload task if is in progress
	    fileRef?.let { outState.putString("reference", it.toString()) }
	    //remove passed listeners
	}

	override fun onRestoreInstanceState(savedInstanceState: Bundle) {
	    super.onRestoreInstanceState(savedInstanceState)

	    //get reference to upload task if was in progress
	    val reference = savedInstanceState.getString("reference")
	    if(reference != null) {
	        fileRef = FirebaseStorage.getInstance().getReferenceFromUrl(reference)

	        //get all UploadTask - in this case there should be one
	        fileRef?.activeUploadTasks?.let { it ->
	            if (it.size > 0) {
	                val task = it[0]
	                //set listeners to task here
	            }
	        }
	    }
	}
}
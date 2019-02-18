class StatusActivity : AppCompatActivity() {

	private lateinit var storageRef: StorageReference

	override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_status)

        storageRef = FirebaseStorage.getInstance().reference

        //some buttons click listeners
    }

    private fun startUploadFileWithListeners() {
	    val file = Uri.fromFile(File("path/file.jpg"))
	    fileRef = storageRef.child("images/${file.lastPathSegment}")

	    //start and add listeners
	    uploadTask = fileRef.putFile(file)
	    uploadTask.addOnProgressListener { taskSnapshot ->
	        //save session URI in persistent storage in case of process die
	        val sessionUri = taskSnapshot.uploadSessionUri
	        //show some progress
	    }.addOnFailureListener {
	        //action on error like show message
	    }.addOnSuccessListener {
	        //action on success like show file in UI
	    }
	}

	private fun restartUploadFile {
	    val file = Uri.fromFile(File("path/file.jpg"))
	    val sessionUri = Uri.EMPTY //mock value, get from persistent storage
	    uploadTask = storageRef.putFile(file, StorageMetadata.Builder().build(), sessionUri)
	}

	//do in the similar way for other operations
}
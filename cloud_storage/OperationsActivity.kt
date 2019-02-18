class OperationsActivity : AppCompatActivity() {

	private lateinit var storageRef: StorageReference

	override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_operations)

        storageRef = FirebaseStorage.getInstance().reference

        //some buttons click listeners
    }

    private fun startUploadFile() {
	    //get file and create reference
	    val file = Uri.fromFile(File("path/file.jpg"))
	    val fileRef = storageRef.child("images/${file.lastPathSegment}")

	    //create custom metadata if needed
	    val metadata = StorageMetadata.Builder().setContentType("image/jpg").build()

	    //upload file
	    val uploadTask = fileRef.putFile(file)
	    //or fileRef.putFile(file, metadata) for using custom metadata
	}

	private fun startDownloadFile() {
	    val file = File.createTempFile("images", "jpg")
	    val fileRef = storageRef.child("images/file.jpg")
	    val downloadTask = fileRef.getFile(file)
	}

	private fun startDeleteFile() {
	    val fileRef = storageRef.child("images/file.jpg")
	    val deleteTask = fileRef.delete()
	}

	private fun startUpdateMetadata() {
	    val fileRef = storageRef.child("images/file.jpg")

	    val metadata = StorageMetadata.Builder()
	        .setContentType("image/jpg")
	        .setCustomMetadata("myCustomProperty", "myValue")
	        .build()

	    val metadataTask = fileRef.updateMetadata(metadata)
	}
}
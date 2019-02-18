class StorageReferenceActivity : AppCompatActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_storage_reference)

        val storageRef = FirebaseStorage.getInstance()
        getReferences(storage)

        val fileRef = storageRef.child("images/file.jpg")
        getInfo(fileRef)
    }
	
	private fun getStorages() {
	    val storage = FirebaseStorage.getInstance() //just get FirebaseStorage instance
	    val customStorage = FirebaseStorage.getInstance("gs://custom-bucket")
	}

	private fun getReferences(storage: StorageReference) {
	    val storageRef = storage.reference
	    val folderRef: StorageReference? = storageRef.child("images")

	    val fileRef = storageRef.child("images/file.jpg")
	    val parentRef = fileRef.parent //equivalent of folderRef
	    val rootRef = fileRef.root //equivalent of storageRef
	    val anotherFileRef = fileRef.parent?.child("anotherFile.png") //chain navigation together
	}

	private fun getInfo(fileRef: StorageReference) {
	    val info = "$fileRef.name in $fileRef.path in $fileRef.bucket bucket"
	    val metadata = fileRef.metadata
	}
}
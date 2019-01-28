class FirebaseAuthActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_firebase_auth)
        auth = FirebaseAuth.getInstance()

        signUpButton.setOnClickListener { updateProfile() }
        signInButton.setOnClickListener { changeEmail() }
    }

    override fun onStart() {
        super.onStart()
        if(auth.currentUser != null) {
            //user is signed in, update UI or navigate
        }
    }

    private fun signUp() {
        //validate fields before
        val email = emailEditText.getText().toString()
        val password = passwordEditText.getText().toString()
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                //sign up success, update UI or navigate
                val user = auth.currentUser
                startActivity(Intent(this, ProfileActivity::class.java))
            }
            else {
                //sign up fails, show error message
            }
        }
    }

    private fun signIn() {
        //validate fields before
        val email = emailEditText.getText().toString()
        val password = passwordEditText.getText().toString()
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                //sign ip success, update UI or navigate
                val user = auth.currentUser
                startActivity(Intent(this, ProfileActivity::class.java))
            }
            else {
                //sign in fails, show error message
            }
        }
    }
}
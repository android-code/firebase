class UserActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

        signOutButton.setOnClickListener {
            AuthUI.getInstance().signOut(this)
                .addOnCompleteListener {
                    //do something like navigate to home screen
                }
        }

        deleteAccountButton.setOnClickListener {
            AuthUI.getInstance().delete(this)
                .addOnCompleteListener {
                    //do something like navigate to home screen
                }
        }
    }

    //more methods
}
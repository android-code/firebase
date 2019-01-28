class ProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        
        showUserInfo()
        showProfiles()

        updateProfileButton.setOnClickListener { updateProfile() }
        changeEmailButton.setOnClickListener { changeEmail() }
        changePasswordButton.setOnClickListener { changePassword() }
        deleteAccountButton.setOnClickListener { deleteAccount() }
        signOutButton.setOnClickListener { signOut() }
    }

    private fun showUserInfo() {
        val user = FirebaseAuth.getInstance().currentUser
        user?.let {
            var info = String()
            info += "uid: ${user.uid}\n"
            info += "name: ${user.displayName}\n"
            info += "email: ${user.email}\n"
            info += "is verified: ${user.isEmailVerified}\n"
            info += "photoUrl: ${user.photoUrl.toString()}\n"
            //get more info
            userInfo.setText(info)
        }
    }

    private fun showProfiles() {
        val user = FirebaseAuth.getInstance().currentUser
        user?.let {
            for(profile in it.providerData) {
                var info = String()
                info += "PROVIDER: ${profile.providerId}\n"
                info += "uid: ${profile.uid}\n"
                info += "name: ${profile.displayName}\n"
                //get more info
                userProfiles.setText(info)
            }
        }
    }

    private fun updateProfile() {
        val user = FirebaseAuth.getInstance().currentUser
        val profileUpdates = UserProfileChangeRequest.Builder()
                .setDisplayName(editText.getText().toString())
                .setPhotoUri(Uri.parse("http://androidcode.pl/assets/img/logo.png"))
                .build()

        user?.updateProfile(profileUpdates)?.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                //show some message and change UI
            }
        }
    }

    private fun changeEmail() {
        val user = FirebaseAuth.getInstance().currentUser
        val email = editText.getText().toString() //validate
        user?.updateEmail(email)?.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                //show some message and change UI
            }
        }
    }

    private fun changePassword() {
        val user = FirebaseAuth.getInstance().currentUser
        val newPassword = editText.getText().toString() //validate
        user?.updatePassword(newPassword)?.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                //show some message and change UI
            }
        }
    }

    private fun deleteAccount() {
        val user = FirebaseAuth.getInstance().currentUser
        user?.delete()?.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                //show some message and change UI
            }
        }
    }

    private fun signOut() {
        FirebaseAuth.getInstance().signOut()
        finish()
    }

    //more methods
}
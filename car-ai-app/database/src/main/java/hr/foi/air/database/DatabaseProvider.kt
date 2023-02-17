package hr.foi.air.database


class DatabaseProvider {

    fun provideDatabase() : DAO
    {
        return FirebaseRepository()
    }
}
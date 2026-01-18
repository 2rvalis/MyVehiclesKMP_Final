// commonMain
@Database(entities = [Vehicle::class], version = 1)
@ConstructedBy(AppDatabaseConstructor::class)
abstract class VehicleDatabase : RoomDatabase() {
    abstract fun vehicleDao(): VehicleDao
}

// Αφαίρεσε το "override" αν υπάρχει, άφησέ το έτσι:
expect object AppDatabaseConstructor : RoomDatabaseConstructor<VehicleDatabase>
package club.someoneice.ovo.json.data

import club.someoneice.ovo.IDataGem
import club.someoneice.ovo.core.Info

class CoreDataOutput(data: JsonData, dataSet: IDataGem) {
    val modid = Info.modid

    init {
        if (Info.dataMode) data.startIn(modid, dataSet)
    }
}
package club.someoneice.ovo.json.data

import club.someoneice.ovo.IDataGem

abstract class JsonData {
    abstract fun startIn(modid: String, dataSet: IDataGem)
}
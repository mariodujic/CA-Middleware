package com.groundzero.camw.features.saints.data

import com.groundzero.camw.core.base.BaseRepository
import com.groundzero.camw.core.data.DataType
import com.groundzero.camw.core.data.ItemMapper
import com.groundzero.camw.core.service.ReadJsonService
import com.groundzero.camw.core.service.WriteJsonService
import com.groundzero.camw.features.saints.constants.SaintsDataType
import com.groundzero.camw.utils.isParentClass
import org.springframework.stereotype.Component

@Component
class SaintsRepository(private val readJson: ReadJsonService, private val writeJson: WriteJsonService, private val mapper: ItemMapper<Saint>) : BaseRepository<Saint> {

    override fun getItems(dataType: DataType): List<Saint>? =
            validateDataPathAndStartAction(dataType, readJson.read(dataType.path))

    override fun addItem(saint: Saint, dataType: DataType) =
            validateDataPathAndStartAction(dataType, writeJson.write(dataType.path, mapper.addItem(saint, getItems(dataType))))

    override fun removeItem(saint: Saint, dataType: DataType) =
            validateDataPathAndStartAction(dataType, writeJson.write(dataType.path, mapper.removeItem(saint, getItems(dataType))))

    private fun <T> validateDataPathAndStartAction(dataType: DataType, action: T): T = if (dataType.isParentClass(SaintsDataType::class)) {
        action
    } else throw IllegalArgumentException()
}
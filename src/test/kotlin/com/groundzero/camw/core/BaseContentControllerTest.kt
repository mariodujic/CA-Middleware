package com.groundzero.camw.core

import com.groundzero.camw.core.base.BaseContentController
import com.groundzero.camw.core.base.BaseContentRepository
import com.groundzero.camw.core.base.BaseContentValidator
import com.groundzero.camw.core.network.NetworkResponse
import com.groundzero.camw.features.information.constants.InformationBlockDataType
import com.groundzero.camw.features.information.data.InformationBlock
import com.groundzero.camw.utils.code
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.http.HttpStatus

@ExtendWith(MockitoExtension::class)
class BaseContentControllerTest {

    @Mock
    private lateinit var baseContentRepository: BaseContentRepository<InformationBlock>

    @Mock
    private lateinit var baseContentValidator: BaseContentValidator

    @InjectMocks
    private lateinit var sut: BaseContentController<InformationBlock>

    @Test
    fun `should invoke getItems once`() {
        sut.getItemsResponse(MOCK_DATA_TYPE)
        verify(baseContentRepository).getItems(MOCK_DATA_TYPE)
    }

    @Test
    fun `should invoke removeItem once`() {
        `when`(baseContentValidator.hasItemId(MOCK_ITEM_ID)).thenReturn(true)
        sut.removeItemResponse(MOCK_DATA, MOCK_DATA_TYPE)
        verify(baseContentRepository).removeItem(MOCK_DATA, MOCK_DATA_TYPE)
    }

    @Test
    fun `should invoke addItem once`() {
        `when`(baseContentValidator.hasItemId(MOCK_ITEM_ID)).thenReturn(true)
        sut.addItemResponse(MOCK_DATA, MOCK_DATA_TYPE)
        verify(baseContentRepository).addItem(MOCK_DATA, MOCK_DATA_TYPE)
    }

    @Test
    fun `should assert equals when getItems is success`() {
        `when`(baseContentValidator.hasItems(MOCK_DATA_LIST)).thenReturn(true)
        `when`(baseContentRepository.getItems(MOCK_DATA_TYPE)).thenReturn(MOCK_DATA_LIST)
        val expectedValue = mockSuccessResponse(MOCK_DATA_LIST)
        val actualValue = sut.getItemsResponse(MOCK_DATA_TYPE)
        assertEquals(expectedValue, actualValue)
    }

    @Test
    fun `should assert equals when getItems is null`() {
        `when`(baseContentRepository.getItems(MOCK_DATA_TYPE)).thenReturn(null)
        val expectedValue = mockErrorResponse()
        val actualValue = sut.getItemsResponse(MOCK_DATA_TYPE)
        assertEquals(expectedValue, actualValue)
    }

    @Test
    fun `should assert equals when removeItem is true`() {
        `when`(baseContentValidator.hasItemId(MOCK_ITEM_ID)).thenReturn(true)
        `when`(baseContentRepository.removeItem(MOCK_DATA, MOCK_DATA_TYPE)).thenReturn(true)
        val expectedValue = mockSuccessResponse(mutableListOf())
        val actualValue = sut.removeItemResponse(MOCK_DATA, MOCK_DATA_TYPE)
        assertEquals(expectedValue, actualValue)
    }

    @Test
    fun `should assert equals when removeItem is false`() {
        `when`(baseContentRepository.removeItem(MOCK_DATA, MOCK_DATA_TYPE)).thenReturn(false)
        `when`(baseContentValidator.hasItemId(MOCK_ITEM_ID)).thenReturn(true)
        val expectedValue = mockErrorResponse()
        val actualValue = sut.removeItemResponse(MOCK_DATA, MOCK_DATA_TYPE)
        assertEquals(expectedValue, actualValue)
    }

    @Test
    fun `should assert equals when addItem is true`() {
        `when`(baseContentValidator.hasItemId(MOCK_ITEM_ID)).thenReturn(true)
        `when`(baseContentRepository.addItem(MOCK_DATA, MOCK_DATA_TYPE)).thenReturn(true)
        val expectedValue = mockSuccessResponse(mutableListOf())
        val actualValue = sut.addItemResponse(MOCK_DATA, MOCK_DATA_TYPE)
        assertEquals(expectedValue, actualValue)
    }

    @Test
    fun `should assert equals when addItem is false`() {
        `when`(baseContentValidator.hasItemId(MOCK_ITEM_ID)).thenReturn(true)
        `when`(baseContentRepository.addItem(MOCK_DATA, MOCK_DATA_TYPE)).thenReturn(false)
        val expectedValue = mockErrorResponse()
        val actualValue = sut.addItemResponse(MOCK_DATA, MOCK_DATA_TYPE)
        assertEquals(expectedValue, actualValue)
    }

    companion object {
        const val MOCK_ITEM_ID = "a"
        val MOCK_DATA_TYPE = InformationBlockDataType.Croatian
        val MOCK_DATA = InformationBlock(itemId = MOCK_ITEM_ID)
        fun mockSuccessResponse(data: List<InformationBlock>) = NetworkResponse.Success(code(HttpStatus.OK), "Success", data)
        val MOCK_DATA_LIST = listOf(InformationBlock())
        fun mockErrorResponse() = NetworkResponse.Error(code(HttpStatus.NOT_FOUND), "Error")
    }
}
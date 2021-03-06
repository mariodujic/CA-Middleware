package com.groundzero.camw

import com.groundzero.camw.core.base.BaseContentRepository
import com.groundzero.camw.core.base.BaseContentValidator
import com.groundzero.camw.core.base.BaseContentValidatorImpl
import com.groundzero.camw.features.prayers.constants.PrayerDataType
import com.groundzero.camw.features.prayers.controller.PrayersContentController
import com.groundzero.camw.features.prayers.data.Prayer
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.`when`
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.setup.MockMvcBuilders


@ExtendWith(MockitoExtension::class)
class PrayersControllerTest {

    @Mock
    private lateinit var contentRepository: BaseContentRepository<Prayer>

    private lateinit var mvc: MockMvc
    private lateinit var contentValidator: BaseContentValidator
    private lateinit var prayersController: PrayersContentController

    @BeforeEach
    fun setUp() {
        contentValidator = BaseContentValidatorImpl()
        prayersController = PrayersContentController(contentRepository, contentValidator)
        mvc = MockMvcBuilders.standaloneSetup(prayersController)
                .build()
    }

    @Test
    fun `en prayers should return correct data`() {
        `when`(contentRepository.getItems(PrayerDataType.English)).thenReturn(mutableListOf(MOCK_PRAYER_EN))
        assertTrue(getResponse("/prayers/en").contains(MOCK_PRAYER_EN.itemId.toString()))
    }

    @Test
    fun `en staging prayers should return correct data`() {
        `when`(contentRepository.getItems(PrayerDataType.EnglishStaging)).thenReturn(mutableListOf(MOCK_PRAYER_EN_STAGING))
        assertTrue(getResponse("/prayers/en-staging").contains(MOCK_PRAYER_EN_STAGING.itemId.toString()))
    }

    @Test
    fun `hr prayers should return correct data`() {
        `when`(contentRepository.getItems(PrayerDataType.Croatian)).thenReturn(mutableListOf(MOCK_PRAYER_HR))
        assertTrue(getResponse("/prayers/hr").contains(MOCK_PRAYER_HR.itemId.toString()))
    }

    @Test
    fun `hr staging prayers should return correct data`() {
        `when`(contentRepository.getItems(PrayerDataType.CroatianStaging)).thenReturn(mutableListOf(MOCK_PRAYER_HR_STAGING))
        assertTrue(getResponse("/prayers/hr-staging").contains(MOCK_PRAYER_HR_STAGING.itemId.toString()))
    }

    private fun getResponse(url: String) = mvc.perform(get(url).accept(MediaType.APPLICATION_JSON)).andReturn().response.contentAsString

    private companion object {
        val MOCK_PRAYER_EN = Prayer("asd42")
        val MOCK_PRAYER_EN_STAGING = Prayer("bj56")
        val MOCK_PRAYER_HR = Prayer("g56")
        val MOCK_PRAYER_HR_STAGING = Prayer("vbn45")
    }
}
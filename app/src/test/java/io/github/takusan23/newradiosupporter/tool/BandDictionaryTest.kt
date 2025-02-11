package io.github.takusan23.newradiosupporter.tool

import org.junit.Assert
import org.junit.Test

/** バンド変換テスト */
class BandDictionaryTest {

    @Test
    fun toLTEBand_EARFCNをバンドに変換できる() {
        val band3 = BandDictionary.toLTEBand(1850)
        Assert.assertEquals(band3, "3")
        val band1 = BandDictionary.toLTEBand(100)
        Assert.assertEquals(band1, "1")
        val band41 = BandDictionary.toLTEBand(40340)
        Assert.assertEquals(band41, "41")
    }

    @Test
    fun toNRBand_NRARFCNをバンドに変換できる() {
        val bandN78 = BandDictionary.toNRBand(643334)
        Assert.assertEquals(bandN78, "n78")
        val bandN79 = BandDictionary.toNRBand(703392)
        Assert.assertEquals(bandN79, "n79")
        val bandList = BandDictionary.toNRBandList(159630)
        Assert.assertEquals(bandList, listOf("n28", "n20"))
        // 700MHz 転用5G
        val bandN28Docomo = BandDictionary.toNRBand(157690)
        Assert.assertEquals(bandN28Docomo, "n28")
        val bandN28Au = BandDictionary.toNRBand(155600)
        Assert.assertEquals(bandN28Au, "n28")
        val bandN28Softbank = BandDictionary.toNRBand(159630)
        Assert.assertEquals(bandN28Softbank, "n28")
    }

    @Test
    fun isMmWave_ミリ波の判定できる() {
        val bandN257 = BandDictionary.isMmWave(2070015)
        Assert.assertEquals(bandN257, true)
        val bandN78 = BandDictionary.isMmWave(643334)
        Assert.assertEquals(bandN78, false)
    }

    @Test
    fun toFrequencyMHz_NRARFCNから周波数を計算できる() {
        val frequency_798 = 159600
        Assert.assertEquals(BandDictionary.toFrequencyMHz(frequency_798), 798f)
        val freqency_3531_36 = 635424
        Assert.assertEquals(BandDictionary.toFrequencyMHz(freqency_3531_36), 3531.36f)
        val freqency_3650_01 = 643334
        Assert.assertEquals(BandDictionary.toFrequencyMHz(freqency_3650_01), 3650.01f)
        val frequency_27450_96 = 2070015
        Assert.assertEquals(BandDictionary.toFrequencyMHz(frequency_27450_96), 27450.96f)
    }

    @Test
    fun isLteFrequency_NRARFCNから転用5Gかどうかの判定ができる() {
        val bandN28 = 157690
        Assert.assertEquals(BandDictionary.isLteFrequency(bandN28), true)
        val bandN78 = 643334
        Assert.assertEquals(BandDictionary.isLteFrequency(bandN78), false)
        val bandN78LteFrequency = 635424
        Assert.assertEquals(BandDictionary.isLteFrequency(bandN78LteFrequency), true)
    }

}
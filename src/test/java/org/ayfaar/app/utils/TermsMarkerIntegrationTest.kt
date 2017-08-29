package org.ayfaar.app.utils

import org.ayfaar.app.IntegrationTest
import org.junit.Ignore
import org.junit.Test

import javax.inject.Inject

import org.junit.Assert.assertEquals
import kotlin.test.assertTrue

class TermsMarkerIntegrationTest : IntegrationTest() {
    @Inject
    lateinit var marker: TermsMarker

    @Test
    @Ignore // эти тести очень дорогие в поддержке
    fun test() {

        val text = "Например, Формо-Творцы АДД-МАДД-ФЛУЙФ-Уровня, организующие высшие ГЛЭИИЙО-реальности"
        //final String expected = "Например, <term id=\"Формо-Творец\">Формо-Творцы</term> " +
        //        "АДД-МАДД-ФЛУЙФ-Уровня, организующие высшие <term id=\"ГЛЭИИЙО\">ГЛЭИИЙО</term>-реальности";
        val expected = "Например, <term id=\"Формо-Творец\">Формо-Творцы</term> АДД-МАДД-ФЛУЙФ-Уровня, " + "организующие высшие <term id=\"ГЛЭИИЙО\">ГЛЭИИЙО</term>-<term id=\"реальность\">реальности</term>"
        val actual = marker.mark(text)
        assertEquals(expected, actual)

        val text2 = "Всё Самосознание ССС-Сущности действительности. Всё Пространство в каждой из своих бесчисленных «резонационных зон» структурировано множеством разнокачественных стоячих волн, которые, как бы накладываясь друг на друга и тем самым либо подавляясь, либо модулируясь, образуют в каждом из частотных Уровней возможного Восприятия абсолютно всю качественную динамику видимой и субъективно переживаемой нами «картинки» окружающей нас в каждый момент действительности."
        val expected2 = "Всё <term id=\"Самосознание ССС-Сущности\">Самосознание ССС-Сущности</term> действительности. Всё <term id=\"Пространство\">Пространство</term> в каждой из своих бесчисленных «<term id=\"Резонационная зона\">резонационных зон</term>» структурировано множеством разнокачественных стоячих волн, которые, как бы накладываясь друг на друга и тем самым либо подавляясь, либо модулируясь, образуют в каждом из частотных Уровней возможного Восприятия абсолютно всю качественную динамику видимой и субъективно переживаемой нами «картинки» окружающей нас в каждый момент действительности."
        val actual2 = marker.mark(text2)
        assertEquals(expected2, actual2)

        val text3 = "Всё Самосознание ССС-Сущности действительности."
        val expected3 = "Всё <term id=\"Самосознание ССС-Сущности\">Самосознание ССС-Сущности</term> действительности."
        val actual3 = marker.mark(text3)
        assertEquals(expected3, actual3)

        val text4 = "Последние отражают собой наличие в Энерго-Плазме того общего Энерго-Потенциала, который необходимо реализовать через Фокусные Динамики Форм Самосознаний абсолютно всех Уровней Энерго-Плазмы для того, чтобы нивелировать образовавшееся в разнокачественных сочетаниях диссонационное состояние и привести общую сллоогрентную фокусную Конфигурацию Мироздания в резонационное, взаимоуравновешивающее состояние. В результате одновременной активности двух Импульс-Потенциалов «внутри» Информации образовались условия для проявления Ею некоего Творческого Потенциала. Это обстоятельство мы обозначили как Энергия нарушенных (изменённых благодаря ИИП и ЭИП) взаимосвязей как между, так и внутри реконверстных Конфигураций, которые следует тут же откорректировать. То есть «внутри» Информации появилась возможность для возникновения особого Универсального Состояния - Энерго-Плазмы, которое ассоциируется с некой неуравновешенностью чего-то одного по отношению к чему-то другому. Энергия здесь отражает диссонационное состояние какой-то части скунккций информационных фрагментов (участки реконверстных Конфигураций, которые не смогли совместиться), в то время как Плазма (ССС) представляет Собой ту часть Информации, которая всегда пребывает в резонационном состоянии. В целом всё это образует собой то активное сллоогрентное Состояние Информации, которое мы интерпретируем как Энерго-Плазма."
        val expected4 = "Последние отражают собой наличие в <term id=\"Энерго-Плазма\">Энерго-Плазме</term> того общего <term id=\"Энерго-Потенциал\">Энерго-Потенциала</term>, который необходимо реализовать через <term id=\"Фокусная Динамика\">Фокусные Динамики</term> <term id=\"Форма\">Форм</term> <term id=\"Самосознание\">Самосознаний</term> абсолютно всех Уровней <term id=\"Энерго-Плазма\">Энерго-Плазмы</term> для того, чтобы нивелировать образовавшееся в разнокачественных сочетаниях диссонационное состояние и привести общую сллоогрентную <term id=\"Фокусная Конфигурация\">фокусную Конфигурацию</term> <term id=\"Мироздание\">Мироздания</term> в резонационное, взаимоуравновешивающее состояние. В результате одновременной активности двух Импульс-Потенциалов «внутри» Информации образовались условия для проявления Ею некоего Творческого Потенциала. Это обстоятельство мы обозначили как <term id=\"Энергия\">Энергия</term> нарушенных (изменённых благодаря <term id=\"ИИП\">ИИП</term> и <term id=\"ЭИП\">ЭИП</term>) взаимосвязей как между, так и внутри <term id=\"реконверстная Конфигурация\">реконверстных Конфигураций</term>, которые следует тут же откорректировать. То есть «внутри» Информации появилась возможность для возникновения особого Универсального Состояния - <term id=\"Энерго-Плазма\">Энерго-Плазмы</term>, которое ассоциируется с некой неуравновешенностью чего-то одного по отношению к чему-то другому. <term id=\"Энергия\">Энергия</term> здесь отражает диссонационное состояние какой-то части скунккций информационных фрагментов (участки <term id=\"реконверстная Конфигурация\">реконверстных Конфигураций</term>, которые не смогли совместиться), в то <term id=\"Время\">время</term> как Плазма (<term id=\"ССС\">ССС</term>) представляет Собой ту часть Информации, которая всегда пребывает в резонационном состоянии. В целом всё это образует собой то активное сллоогрентное Состояние Информации, которое мы интерпретируем как <term id=\"Энерго-Плазма\">Энерго-Плазма</term>."
        val actual4 = marker.mark(text4)
        assertEquals(expected4, actual4)

        //2.0372
        val text5 = "Каждый очередной момент Синтеза тех или иных Аспектов Качеств в ф-Конфигурации Формы Самосознания характеризуется лишь определённым увеличением концентрации содержащейся в ней разно-Качественной Информации – за счёт повышения степени коварллертности образующих Её взаимосвязей. А повышение концентрации - это вовсе не «абсолютное сплавление» Аспектов друг с другом (насколько бы они ни были идентичны – лийллусцивны - по Смыслу свойственной каждому из Них Информации), а Их способность к постоянно увеличивающемуся сосредоточению (собиранию, скапливанию, агрегации) в какой-то единице объёма Информации, которую в Пространстве-Времени и выражает та или иная Форма проявления Самосознания."
        val expected5 = "Каждый очередной момент <term id=\"Синтез\">Синтеза</term> тех или иных <term id=\"Аспект\">Аспектов</term> Качеств в <term id=\"ф-Конфигурация\">ф-Конфигурации</term> <term id=\"Форма Самосознания\">Формы Самосознания</term> характеризуется лишь определённым увеличением концентрации содержащейся в ней разно-Качественной Информации - за счёт повышения степени <term id=\"Коварллертность\">коварллертности</term> образующих Её взаимосвязей. А повышение концентрации - это вовсе не «абсолютное сплавление» <term id=\"Аспект\">Аспектов</term> друг с другом (насколько бы они ни были идентичны - лийллусцивны - по Смыслу свойственной каждому из Них Информации), а Их способность к постоянно увеличивающемуся сосредоточению (собиранию, скапливанию, агрегации) в какой-то единице объёма Информации, которую в <term id=\"Пространство-Время\">Пространстве-Времени</term> и выражает та или иная <term id=\"Форма\">Форма</term> проявления <term id=\"Самосознание\">Самосознания</term>."
        val actual5 = marker.mark(text5)
        assertEquals(expected5, actual5)

        val text6 = " Качеств ВСЕ-Любовь–ВСЕ-Мудрость и ВСЕ-Воля–ВСЕ-Разума в "
        val expected6 = " Качеств <term id=\"ВСЕ-Любовь-ВСЕ-Мудрость\">ВСЕ-Любовь-ВСЕ-Мудрость</term> и <term id=\"ВСЕ-Воля-ВСЕ-Разума\">ВСЕ-Воля-ВСЕ-Разума</term> в "
        val actual6 = marker.mark(text6)
        assertEquals(expected6, actual6)


        //2.0382
        val text7 = "Вся эта симультанно, Вся эта -симультанно,"
        val expected7 = "Вся эта <term id=\"Симультанно\" has-short-description=\"true\">симультанно</term>, Вся эта -<term id=\"Симультанно\" has-short-description=\"true\">симультанно</term>,"
        val actual7 = marker.mark(text7)
        assertEquals(expected7, actual7)

        val text8 = "организующие ГЛЭИИЙО-мерность) имеют"
        val expected8 = "организующие <term id=\"ГЛЭИИЙО-мерность\">ГЛЭИИЙО-мерность</term>) имеют"
        val actual8 = marker.mark(text8)
        assertEquals(expected8, actual8)

    }

    @Test
    @Ignore
    fun test2() {
        assertEquals(" пост<term id=\"Меркавгнация\" has-short-description=\"true\">меркавгнационных</term>, ", marker.mark(" постмеркавгнационных, "))
        assertEquals(" меж<term id=\"Скунккция\" title=\"Скунккция\">скунккциональные</term> взаимосвязи, ", marker.mark(" межскунккциональные взаимосвязи, "))
    }

    @Test
    fun testItems() {
        assertEquals("<uri>1.0001</uri>", marker.mark("1.0001", true))
        assertEquals("[<uri>1.0001</uri>]", marker.mark("[1.0001]", true))
    }

    @Test
    @Ignore
    fun testQuotes() {
        //TODO: implement
        assertTrue { marker.mark("«личностное» Самосознание").matches(Regex("<term.+>«личностное» Самосознание</term>")) }
    }
}

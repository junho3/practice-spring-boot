package com.example.demo

import com.navercorp.fixturemonkey.FixtureMonkey
import com.navercorp.fixturemonkey.FixtureMonkeyBuilder
import com.navercorp.fixturemonkey.kotlin.KotlinPlugin

object FixturesMonkey {
    private fun fixtureMonkeyBuilder(): FixtureMonkeyBuilder {
        return FixtureMonkey.builder()
            .plugin(KotlinPlugin())
    }

    fun fixture(fixtureMonkeyBuilder: FixtureMonkeyBuilder = fixtureMonkeyBuilder()): FixtureMonkey {
        return fixtureMonkeyBuilder
            .build()
    }
}

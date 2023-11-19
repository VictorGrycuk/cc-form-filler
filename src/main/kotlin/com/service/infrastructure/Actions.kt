package com.service.infrastructure

import com.service.core.action.AramexAction
import com.service.core.action.FakeCorreoArgentinoAction

object Actions {
    val fakeCorreoArgentinoAction by lazy {
        FakeCorreoArgentinoAction(Services.fakeCorreoArgentinoService)
    }

    val aramexAction by lazy {
        AramexAction(Services.aramexService)
    }
}
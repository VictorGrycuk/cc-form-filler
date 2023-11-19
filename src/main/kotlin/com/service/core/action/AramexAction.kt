package com.service.core.action

import com.service.core.domain.service.ScamWebsiteService

class AramexAction(
    private val website: ScamWebsiteService
) {
    operator fun invoke() = website.completeForm()
}
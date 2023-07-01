package com.marcosrocha85.events.data.service.memory.mapper

import com.marcosrocha85.events.data.base.BaseMapper
import com.marcosrocha85.events.data.service.memory.model.SkillData
import com.marcosrocha85.events.domain.model.Skill

class SkillMapper : BaseMapper<SkillData, Skill>() {
    override fun dataToModel(data: SkillData): Skill {
        return Skill(
            data.skillName,
            data.sortOrder
        )
    }

    override fun modelToData(model: Skill): SkillData {
        return SkillData(
            model.name,
            model.priority
        )
    }
}
package com.example.githubrepo.models
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "repos")
data class Repo (
    @PrimaryKey(autoGenerate = true)
    val repo_id: Int,
    val name: String,
    val display_name: String?,
    val short_description: String?,
    val description: String?,
    val created_by: String?
)

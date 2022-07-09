package com.inkapplications.telegram.structures

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * This object represents an incoming update.
 *
 * At most one of the optional parameters can be present in any given update.
 */
@Serializable
data class Update(
    /**
     * The update's unique identifier.
     *
     * Update identifiers start from a certain positive number and increase
     * sequentially.
     * This ID becomes especially handy if you're using webhooks,
     * since it allows you to ignore repeated updates or to restore
     * the correct update sequence, should they get out of order.
     *
     * If there are no new updates for at least a week, then identifier
     * of the next update will be chosen randomly instead of sequentially.
     */
    @SerialName("update_id")
    val id: Long,

    /**
     * Optional. New incoming message of any kind - text, photo, sticker, etc.
     */
    val message: Message? = null,

    /**
     * Optional. New incoming message of any kind - text, photo, sticker, etc.
     */
    @SerialName("edited_message")
    val editedMessage: Message? = null,

    /**
     * Optional. New incoming channel post of any kind -
     * text, photo, sticker, etc.
     */
    @SerialName("channel_post")
    val channelPost: Message? = null,

    /**
     * Optional. New version of a channel post that is known to the bot
     * and was edited
     */
    @SerialName("edited_channel_post")
    val editedChannelPost: Message? = null,

    /**
     * Optional. New incoming inline query
     */
    @SerialName("inline_query")
    val inlineQuery: InlineQuery? = null,

    // TODO: More fields in this object.
)


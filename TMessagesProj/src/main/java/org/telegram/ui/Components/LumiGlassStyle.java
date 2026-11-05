
package org.telegram.ui.Components;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

import org.telegram.messenger.AndroidUtilities;

/**
 * LumiGlass visual helpers for the chat list.
 * Draws decoration only and does not change Telegram chat functionality.
 */
public final class LumiGlassStyle {

    private static final Paint CARD_FILL = new Paint(Paint.ANTI_ALIAS_FLAG);
    private static final Paint CARD_STROKE = new Paint(Paint.ANTI_ALIAS_FLAG);
    private static final Paint TOP_HIGHLIGHT = new Paint(Paint.ANTI_ALIAS_FLAG);

    private static final RectF CARD_RECT = new RectF();
    private static final RectF HIGHLIGHT_RECT = new RectF();

    private LumiGlassStyle() {
    }

    public static void drawChatCard(
            Canvas canvas,
            float width,
            float height,
            boolean selected
    ) {
        if (canvas == null || width <= 0 || height <= 0) {
            return;
        }

        float sideInset = AndroidUtilities.dp(6);
        float verticalInset = AndroidUtilities.dp(3);
        float radius = AndroidUtilities.dp(17);

        CARD_RECT.set(
                sideInset,
                verticalInset,
                Math.max(sideInset, width - sideInset),
                Math.max(verticalInset, height - verticalInset)
        );

        // Translucent glass surface.
        CARD_FILL.setStyle(Paint.Style.FILL);
        CARD_FILL.setColor(selected ? 0x2EFFFFFF : 0x1EFFFFFF);
        canvas.drawRoundRect(CARD_RECT, radius, radius, CARD_FILL);

        // Thin glass outline.
        CARD_STROKE.setStyle(Paint.Style.STROKE);
        CARD_STROKE.setStrokeWidth(AndroidUtilities.dp(1));
        CARD_STROKE.setColor(selected ? 0x70FFFFFF : 0x42FFFFFF);
        canvas.drawRoundRect(CARD_RECT, radius, radius, CARD_STROKE);

        // Subtle reflection along the upper edge.
        float inset = AndroidUtilities.dp(2);

        HIGHLIGHT_RECT.set(
                CARD_RECT.left + inset,
                CARD_RECT.top + inset,
                CARD_RECT.right - inset,
                CARD_RECT.top + AndroidUtilities.dp(2.7f)
        );

        TOP_HIGHLIGHT.setStyle(Paint.Style.FILL);
        TOP_HIGHLIGHT.setColor(selected ? 0x38FFFFFF : 0x20FFFFFF);

        canvas.drawRoundRect(
                HIGHLIGHT_RECT,
                AndroidUtilities.dp(1.4f),
                AndroidUtilities.dp(1.4f),
                TOP_HIGHLIGHT
        );
    }
}

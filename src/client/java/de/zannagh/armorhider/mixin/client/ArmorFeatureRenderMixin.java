package de.zannagh.armorhider.mixin.client;

import de.zannagh.armorhider.ArmorModificationInfo;
import de.zannagh.armorhider.ArmorTransparencyHelper;
import de.zannagh.armorhider.ClientConfigManager;
import de.zannagh.armorhider.PlayerConfig;
import net.minecraft.client.render.command.OrderedRenderCommandQueue;
import net.minecraft.client.render.entity.feature.ArmorFeatureRenderer;
import net.minecraft.client.render.entity.state.BipedEntityRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ArmorFeatureRenderer.class)
public class ArmorFeatureRenderMixin {
    @Inject(method = "render", at = @At("RETURN"))
    public void clearRenderState(CallbackInfo ci) {
        ArmorTransparencyHelper.clearCurrentPlayerContext();
    }

    @Inject(method = "renderArmor", at = @At("HEAD"), cancellable = true)
    private void captureSlotAndCheckHide(
            MatrixStack matrices,
            OrderedRenderCommandQueue vertexConsumers,
            ItemStack stack,
            EquipmentSlot slot,
            int light,
            BipedEntityRenderState armorModel,
            CallbackInfo ci) {

        PlayerConfig config = ClientConfigManager.getConfigForPlayer(ArmorTransparencyHelper.getCurrentPlayerUuid());
        var modificationInfo = new ArmorModificationInfo(slot, config);
        
        if (modificationInfo.ShouldHide()) {
            ArmorTransparencyHelper.setCurrentSlotInfo(modificationInfo);
            ci.cancel();
            return;
        }

        if (modificationInfo.ShouldModify()) {
            ArmorTransparencyHelper.setCurrentSlotInfo(modificationInfo);
        }
    }

    @Inject(method = "renderArmor", at = @At("RETURN"))
    private void clearSlot(CallbackInfo ci) {
        ArmorTransparencyHelper.clearCurrentArmorSlot();
    }
}
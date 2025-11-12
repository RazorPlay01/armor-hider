package de.zannagh.armorhider.mixin.client;

import de.zannagh.armorhider.ArmorTransparencyHelper;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntityRenderer.class)
public class LivingEntityRenderMixin {
    @Inject(
            method = "hasLabel(Lnet/minecraft/entity/LivingEntity;D)Z",
            at = @At("HEAD"),
            cancellable = true
    )
    private <T extends LivingEntity> void accessLabel(T livingEntity, double d, CallbackInfoReturnable<Boolean> cir) {
       var entity = livingEntity;
       if (entity instanceof PlayerEntity) {
           ArmorTransparencyHelper.setCurrentPlayerUuid(((PlayerEntity) entity).getGameProfile().id());
       }
    }
}

package com.souldi.origins_winter_fabric.item;

import com.souldi.origins_winter_fabric.client.renderer.WinterFairyArmorRenderer;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemStack;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.animatable.client.RenderProvider;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.constant.DefaultAnimations;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class WinterFairyArmorItem extends ArmorItem implements GeoItem {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    private final Supplier<Object> renderProvider = GeoItem.makeRenderer(this);

    public WinterFairyArmorItem(ArmorMaterial mat, Type type, Settings s){super(mat, type, s);}

    @Override public void createRenderer(Consumer<Object> cons){
        cons.accept(new RenderProvider(){
            private WinterFairyArmorRenderer renderer;
            @Override public BipedEntityModel<LivingEntity> getHumanoidArmorModel(LivingEntity e, ItemStack st, EquipmentSlot sl, BipedEntityModel<LivingEntity> orig){
                if(renderer==null)renderer=new WinterFairyArmorRenderer();
                renderer.prepForRender(e,st,sl,orig);return renderer;}
        });
    }
    @Override public Supplier<Object> getRenderProvider(){return renderProvider;}
    @Override public void registerControllers(AnimatableManager.ControllerRegistrar r){
        r.add(new AnimationController<>(this,state->{
            state.getController().setAnimation(DefaultAnimations.IDLE);
            return state.getData(DataTickets.ENTITY)!=null?PlayState.CONTINUE:PlayState.STOP;
        }));
    }
    @Override public AnimatableInstanceCache getAnimatableInstanceCache(){return cache;}
}
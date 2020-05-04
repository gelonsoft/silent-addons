/*
 * Silent Gear -- CoreExcavator
 * Copyright (C) 2018 SilentChaos512
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation version 3
 * of the License.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package ru.gelonsoft.silentaddons.item.gear;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;
import net.silentchaos512.gear.api.item.GearType;
import net.silentchaos512.gear.api.stats.ItemStat;
import net.silentchaos512.gear.api.stats.ItemStats;
import net.silentchaos512.gear.api.stats.StatInstance;
import net.silentchaos512.gear.item.gear.CoreExcavator;
import net.silentchaos512.gear.item.gear.CoreShovel;
import net.silentchaos512.gear.util.IAOETool;


import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CoreMegaExcavator extends CoreExcavator implements IAOETool {
    @Override
    public GearType getGearType() {
        return GearType.EXCAVATOR;
    }

    @Nonnull
    @Override
    public ToolType getAOEToolClass() {
        return ToolType.SHOVEL;
    }

    @Nullable
    @Override
    public RayTraceResult rayTraceBlocks(World world, PlayerEntity player) {
        return rayTrace(world, player, RayTraceContext.FluidMode.NONE);
    }

    @Override
    public Optional<StatInstance> getBaseStatModifier(ItemStat stat) {
        if (stat == ItemStats.MELEE_DAMAGE)
            return Optional.of(StatInstance.makeBaseMod(2));
        if (stat == ItemStats.ATTACK_SPEED)
            return Optional.of(StatInstance.makeBaseMod(-3));
        if (stat == ItemStats.REPAIR_EFFICIENCY)
            return Optional.of(StatInstance.makeBaseMod(1.5f));
        return Optional.empty();
    }

    @Override
    public Optional<StatInstance> getStatModifier(ItemStat stat) {
        if (stat == ItemStats.DURABILITY)
            return Optional.of(StatInstance.makeGearMod(1.0f));
        if (stat == ItemStats.ENCHANTABILITY)
            return Optional.of(StatInstance.makeGearMod(-0.5f));
        if (stat == ItemStats.HARVEST_SPEED)
            return Optional.of(StatInstance.makeGearMod(-0.5f));
        return Optional.empty();
    }

    @Override
    public boolean onBlockStartBreak(ItemStack itemstack, BlockPos pos, PlayerEntity player) {
        return IAOETool.BreakHandler.onBlockStartBreak(itemstack, pos, player);
    }

    @Override
    public List<BlockPos> getExtraBlocks(World world, @Nullable BlockRayTraceResult rt, PlayerEntity player, ItemStack stack) {
        List<BlockPos> positions = new ArrayList<>();

        if (player.isSneaking() || rt == null || rt.getPos() == null || rt.getFace() == null)
            return positions;

        BlockPos pos = rt.getPos();
        BlockState state = world.getBlockState(pos);

        if (isEffectiveOnBlock(stack, world, pos, state)) {
            switch (rt.getFace().getAxis()) {
                case Y:
                    attemptAddExtraBlock(world, state, pos.offset(Direction.NORTH), stack, positions);
                    attemptAddExtraBlock(world, state, pos.offset(Direction.NORTH,2), stack, positions);
                    attemptAddExtraBlock(world, state, pos.offset(Direction.NORTH).offset(Direction.EAST), stack, positions);
                    attemptAddExtraBlock(world, state, pos.offset(Direction.NORTH,2).offset(Direction.EAST), stack, positions);
                    attemptAddExtraBlock(world, state, pos.offset(Direction.NORTH).offset(Direction.EAST,2), stack, positions);
                    attemptAddExtraBlock(world, state, pos.offset(Direction.NORTH,2).offset(Direction.EAST,2), stack, positions);
                    attemptAddExtraBlock(world, state, pos.offset(Direction.EAST), stack, positions);
                    attemptAddExtraBlock(world, state, pos.offset(Direction.EAST,2), stack, positions);
                    attemptAddExtraBlock(world, state, pos.offset(Direction.EAST).offset(Direction.SOUTH), stack, positions);
                    attemptAddExtraBlock(world, state, pos.offset(Direction.EAST,2).offset(Direction.SOUTH), stack, positions);
                    attemptAddExtraBlock(world, state, pos.offset(Direction.EAST).offset(Direction.SOUTH,2), stack, positions);
                    attemptAddExtraBlock(world, state, pos.offset(Direction.EAST,2).offset(Direction.SOUTH,2), stack, positions);
                    attemptAddExtraBlock(world, state, pos.offset(Direction.SOUTH), stack, positions);
                    attemptAddExtraBlock(world, state, pos.offset(Direction.SOUTH,2), stack, positions);
                    attemptAddExtraBlock(world, state, pos.offset(Direction.SOUTH).offset(Direction.WEST), stack, positions);
                    attemptAddExtraBlock(world, state, pos.offset(Direction.SOUTH,2).offset(Direction.WEST), stack, positions);
                    attemptAddExtraBlock(world, state, pos.offset(Direction.SOUTH).offset(Direction.WEST,2), stack, positions);
                    attemptAddExtraBlock(world, state, pos.offset(Direction.SOUTH,2).offset(Direction.WEST,2), stack, positions);
                    attemptAddExtraBlock(world, state, pos.offset(Direction.WEST), stack, positions);
                    attemptAddExtraBlock(world, state, pos.offset(Direction.WEST,2), stack, positions);
                    attemptAddExtraBlock(world, state, pos.offset(Direction.WEST).offset(Direction.NORTH), stack, positions);
                    attemptAddExtraBlock(world, state, pos.offset(Direction.WEST,2).offset(Direction.NORTH), stack, positions);
                    attemptAddExtraBlock(world, state, pos.offset(Direction.WEST).offset(Direction.NORTH,2), stack, positions);
                    attemptAddExtraBlock(world, state, pos.offset(Direction.WEST,2).offset(Direction.NORTH,2), stack, positions);
                    break;
                case X:
                    attemptAddExtraBlock(world, state, pos.offset(Direction.NORTH), stack, positions);
                    attemptAddExtraBlock(world, state, pos.offset(Direction.NORTH,2), stack, positions);
                    attemptAddExtraBlock(world, state, pos.offset(Direction.NORTH).offset(Direction.UP), stack, positions);
                    attemptAddExtraBlock(world, state, pos.offset(Direction.NORTH,2).offset(Direction.UP), stack, positions);
                    attemptAddExtraBlock(world, state, pos.offset(Direction.NORTH).offset(Direction.UP,2), stack, positions);
                    attemptAddExtraBlock(world, state, pos.offset(Direction.NORTH,2).offset(Direction.UP,2), stack, positions);
                    attemptAddExtraBlock(world, state, pos.offset(Direction.UP), stack, positions);
                    attemptAddExtraBlock(world, state, pos.offset(Direction.UP,2), stack, positions);
                    attemptAddExtraBlock(world, state, pos.offset(Direction.UP).offset(Direction.SOUTH), stack, positions);
                    attemptAddExtraBlock(world, state, pos.offset(Direction.UP,2).offset(Direction.SOUTH), stack, positions);
                    attemptAddExtraBlock(world, state, pos.offset(Direction.UP).offset(Direction.SOUTH,2), stack, positions);
                    attemptAddExtraBlock(world, state, pos.offset(Direction.UP,2).offset(Direction.SOUTH,2), stack, positions);
                    attemptAddExtraBlock(world, state, pos.offset(Direction.SOUTH), stack, positions);
                    attemptAddExtraBlock(world, state, pos.offset(Direction.SOUTH,2), stack, positions);
                    attemptAddExtraBlock(world, state, pos.offset(Direction.SOUTH).offset(Direction.DOWN), stack, positions);
                    attemptAddExtraBlock(world, state, pos.offset(Direction.SOUTH,2).offset(Direction.DOWN), stack, positions);
                    attemptAddExtraBlock(world, state, pos.offset(Direction.SOUTH).offset(Direction.DOWN,2), stack, positions);
                    attemptAddExtraBlock(world, state, pos.offset(Direction.SOUTH,2).offset(Direction.DOWN,2), stack, positions);
                    attemptAddExtraBlock(world, state, pos.offset(Direction.DOWN), stack, positions);
                    attemptAddExtraBlock(world, state, pos.offset(Direction.DOWN,2), stack, positions);
                    attemptAddExtraBlock(world, state, pos.offset(Direction.DOWN).offset(Direction.NORTH), stack, positions);
                    attemptAddExtraBlock(world, state, pos.offset(Direction.DOWN,2).offset(Direction.NORTH), stack, positions);
                    attemptAddExtraBlock(world, state, pos.offset(Direction.DOWN).offset(Direction.NORTH,2), stack, positions);
                    attemptAddExtraBlock(world, state, pos.offset(Direction.DOWN,2).offset(Direction.NORTH,2), stack, positions);
                    break;
                case Z:
                    attemptAddExtraBlock(world, state, pos.offset(Direction.DOWN), stack, positions);
                    attemptAddExtraBlock(world, state, pos.offset(Direction.DOWN,2), stack, positions);
                    attemptAddExtraBlock(world, state, pos.offset(Direction.DOWN).offset(Direction.EAST), stack, positions);
                    attemptAddExtraBlock(world, state, pos.offset(Direction.DOWN,2).offset(Direction.EAST), stack, positions);
                    attemptAddExtraBlock(world, state, pos.offset(Direction.DOWN).offset(Direction.EAST,2), stack, positions);
                    attemptAddExtraBlock(world, state, pos.offset(Direction.DOWN,2).offset(Direction.EAST,2), stack, positions);
                    attemptAddExtraBlock(world, state, pos.offset(Direction.EAST), stack, positions);
                    attemptAddExtraBlock(world, state, pos.offset(Direction.EAST,2), stack, positions);
                    attemptAddExtraBlock(world, state, pos.offset(Direction.EAST).offset(Direction.UP), stack, positions);
                    attemptAddExtraBlock(world, state, pos.offset(Direction.EAST,2).offset(Direction.UP), stack, positions);
                    attemptAddExtraBlock(world, state, pos.offset(Direction.EAST).offset(Direction.UP,2), stack, positions);
                    attemptAddExtraBlock(world, state, pos.offset(Direction.EAST,2).offset(Direction.UP,2), stack, positions);
                    attemptAddExtraBlock(world, state, pos.offset(Direction.UP), stack, positions);
                    attemptAddExtraBlock(world, state, pos.offset(Direction.UP,2), stack, positions);
                    attemptAddExtraBlock(world, state, pos.offset(Direction.UP).offset(Direction.WEST), stack, positions);
                    attemptAddExtraBlock(world, state, pos.offset(Direction.UP,2).offset(Direction.WEST), stack, positions);
                    attemptAddExtraBlock(world, state, pos.offset(Direction.UP).offset(Direction.WEST,2), stack, positions);
                    attemptAddExtraBlock(world, state, pos.offset(Direction.UP,2).offset(Direction.WEST,2), stack, positions);
                    attemptAddExtraBlock(world, state, pos.offset(Direction.WEST), stack, positions);
                    attemptAddExtraBlock(world, state, pos.offset(Direction.WEST,2), stack, positions);
                    attemptAddExtraBlock(world, state, pos.offset(Direction.WEST).offset(Direction.DOWN), stack, positions);
                    attemptAddExtraBlock(world, state, pos.offset(Direction.WEST,2).offset(Direction.DOWN), stack, positions);
                    attemptAddExtraBlock(world, state, pos.offset(Direction.WEST).offset(Direction.DOWN,2), stack, positions);
                    attemptAddExtraBlock(world, state, pos.offset(Direction.WEST,2).offset(Direction.DOWN,2), stack, positions);
                    break;
            }
        }
//        SilentGear.log.debug("{}", positions);
        return positions;
    }
    public void attemptAddExtraBlock(World world, BlockState state1, BlockPos pos2, ItemStack stack, List<BlockPos> list) {
        final BlockState state2 = world.getBlockState(pos2);
        // Prevent breaking of unbreakable blocks, like bedrock
        if (state2.getBlockHardness(world, pos2) < 0) return;

        if (!world.isAirBlock(pos2)
                && (state2.getBlock().isToolEffective(state2, getAOEToolClass()) || stack.getItem().canHarvestBlock(stack, state2))) {
            list.add(pos2);
        }
    }
}

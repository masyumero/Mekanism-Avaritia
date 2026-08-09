package io.github.masyumero.mekavaritia.datagen.client.lang;

import io.github.masyumero.mekavaritia.MekanismAvaritiaLang;
import io.github.masyumero.mekavaritia.common.registry.MAModules;
import net.minecraft.data.PackOutput;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class JapaneseLangProvider extends BaseLanguageProvider implements IEnglishToAnyLanguageProvider {

    private static final Map<String, String> EN_JP_WORDS = new HashMap<>();

    public JapaneseLangProvider(PackOutput output) {
        super(output, "ja_jp");
    }

    public void addWord(String en, String jp) {
        EN_JP_WORDS.put(en.toLowerCase(Locale.ROOT), jp);
    }

    @Override
    protected void addTranslations() {
        addMisc();

        LANGS.forEach((key, en) -> add(key, replaceEN(en)));
    }

    @Override
    public String getAnyLangWord(String enKey) {
        return EN_JP_WORDS.get(enKey);
    }

    @Override
    public void registerWords() {
        // Tier
        addWord("prismatic", "彩晶");
        addWord("flare", "閃光");
        addWord("neural", "神経");
        addWord("eternal", "永遠");
        // Machine
        addWord("electric", "工業用");
        addWord("collector", "収集機");
        addWord("compressor", "圧縮機");
        // Factory
        addWord("factory", "ファクトリー");
        addWord("alloying", "合金");
        addWord("centrifuging", "分離");
        addWord("combining", "結合");
        addWord("compressing", "圧縮");
        addWord("crushing", "粉砕");
        addWord("crystallizing", "結晶");
        addWord("dissolving", "溶解");
        addWord("enriching", "濃縮");
        addWord("infusing", "吹込");
        addWord("injecting", "注入");
        addWord("lathing", "旋盤");
        addWord("liquifying", "液化");
        addWord("oxidizing", "酸化");
        addWord("painting", "塗装");
        addWord("pigment", "顔料");
        addWord("extracting", "抽出");
        addWord("planting", "栽培");
        addWord("pressurised", "");
        addWord("reacting", "反応");
        addWord("purifying", "浄化");
        addWord("recycling", "再環");
        addWord("replicating", "複製");
        addWord("rolling", "圧延");
        addWord("mill", "");
        addWord("sawing", "製材");
        addWord("smelting", "精錬");
        addWord("stamping", "打圧");
        addWord("washing", "洗浄");
        // Transporter
        addWord("logistical", "物流");
        addWord("mechanical", "メカニカル");
        addWord("pressurized", "加圧");
        addWord("thermodynamic", "熱力学的");
        addWord("universal", "ユニバーサル");
        addWord("transporter", "トランスポーター");
        addWord("pipe", "パイプ");
        addWord("tube", "チューブ");
        addWord("conductor", "コンダクター");
        addWord("cable", "ケーブル");
        // Induction
        addWord("induction", "インダクション");
        addWord("cell", "セル");
        addWord("provider", "プロバイダ");
        // Tier Installer
        addWord("tier", "ティア");
        addWord("installer", "インストーラー");
        // Circuit
        addWord("base", "基礎");
        addWord("control", "制御");
        addWord("circuit", "回路");
        // Alloy
        addWord("alloy", "合金");
        addWord("crystalline", "結晶");
        addWord("blazing", "灼熱");
        addWord("neutron", "中性子");
        addWord("infinity", "無限");
        // Material
        addWord("dust", "の粉");
        addWord("enriched", "濃縮");
    }

    private void addMisc() {
        add(MekanismAvaritiaLang.TAB, "Mekanism Avaritia");
        add(MekanismAvaritiaLang.ANTIMATTER_PELLET_SINGULARITY, "反物質ペレット");
        add(MekanismAvaritiaLang.CRYSTALLINE_ALLOY_SINGULARITY, "結晶合金");
        add(MekanismAvaritiaLang.DESCRIPTION_ELECTRIC_NEUTRON_COLLECTOR, "工業用のニュートロン収集機です。制御回路によって収集速度が変動します。");
        add(MekanismAvaritiaLang.DESCRIPTION_ELECTRIC_SINGULARITY_COMPRESSOR, "工業用のニュートロニウム圧縮機です。");
        add(MAModules.COSMIC_UNIT.getModuleData(), "宇宙ユニット", "宇宙の力を授ける");
        add(MAModules.INFINITY_ENERGY_UNIT.getModuleData(), "無限エネルギーユニット", "無限のエネルギーを提供する");
        add(MAModules.INFINITY_ELYTRA_UNIT.getModuleData(), "インフィニティエリトラユニット", "MekaSuitにインフィニティエリトラを追加します。");
        add(MAModules.INFINITY_EXCAVATION_ESCALATION_UNIT.getModuleData(), "無限採掘漸増", "あらゆるブロックに対する採掘速度を増加します。");
        add(MAModules.CELESTIAL_UNIT.getModuleData(), "天体ユニット", "MekaSuitにインフィニティ装備の力を与えます。");
        add(MAModules.NEBULIGHT_UNIT.getModuleData(), "星光ユニット", "MekaSuitにインフィニティ装備の力を与えます。");
        add(MAModules.STARFEAST_UNIT.getModuleData(), "星祭ユニット", "MekaSuitにインフィニティ装備の力を与えます。");
        add(MAModules.LIGHTSPEED_UNIT.getModuleData(), "光速ユニット", "MekaSuitにインフィニティ装備の力を与えます。");
        add(MAModules.INFINITY_ATTACK_AMPLIFICATION_UNIT.getModuleData(), "無限攻撃力増強ユニット", "プレイヤーやモブに対する近接攻撃を強化します。");
        add(MAModules.COSMIC_STRIKE_UNIT.getModuleData(), "宇宙打撃ユニット", "MekaTanaにインフィニティ装備の力を与えます。");
        add(MAModules.CELESTIAL_SHOT_UNIT.getModuleData(), "天体射撃ユニット", "MekaBowにインフィニティ装備の力を与えます。");
        add(MAModules.INFINITY_DAMAGE_UNIT.getModuleData(), "無限ダメージユニット", "攻撃力を大幅に上昇させます。");

    }
}

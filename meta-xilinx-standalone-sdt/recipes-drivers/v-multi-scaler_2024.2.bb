inherit features_check

REQUIRED_MACHINE_FEATURES = "v-multi-scaler"

inherit esw python3native

DEPENDS += "xilstandalone video-common"

ESW_COMPONENT_SRC = "/XilinxProcessorIPLib/drivers/v_multi_scaler/src/"
ESW_COMPONENT_NAME = "libv_multi_scaler.a"

addtask do_generate_driver_data before do_configure after do_prepare_recipe_sysroot
do_prepare_recipe_sysroot[rdeptask] = "do_unpack"

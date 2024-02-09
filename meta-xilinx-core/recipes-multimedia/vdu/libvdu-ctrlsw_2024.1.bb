SUMMARY = "Control Software for VDU"
DESCRIPTION = "Control software libraries, test applications and headers provider for VDU"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE.md;md5=002a0a92906100955ea6ed02dcd2c2cd"

XILINX_VDU_VERSION = "1.0.0"
PV =. "${XILINX_VDU_VERSION}-xilinx-v"
PV .= "+git${SRCPV}"

inherit autotools features_check

REQUIRED_MACHINE_FEATURES = "vdu"

BRANCH ?= "master"
REPO   ?= "git://github.com/Xilinx/vdu-ctrl-sw.git;protocol=https"
SRCREV ?= "db37dc935785dff57a52eabc41ac0bf26b0a1707"

BRANCHARG = "${@['nobranch=1', 'branch=${BRANCH}'][d.getVar('BRANCH', True) != '']}"
SRC_URI = "${REPO};${BRANCHARG}"

S  = "${WORKDIR}/git"

COMPATIBLE_MACHINE = "^$"
COMPATIBLE_MACHINE:versal-ai-core = "versal-ai-core"
COMPATIBLE_MACHINE:versal-ai-edge = "versal-ai-edge"

PACKAGE_ARCH = "${SOC_FAMILY_ARCH}"

RDEPENDS:${PN} = "kernel-module-vdu"

do_compile[dirs] = "${S}"
do_install[dirs] = "${S}"

EXTRA_OEMAKE = "CC='${CC}' CXX='${CXX} ${CXXFLAGS}'"
EXTRA_OEMAKE +=" INSTALL_HDR_PATH=${D}${includedir}/vdu-ctrl-sw/include INSTALL_PATH=${D}${bindir}"

do_install:append() {

    oe_libinstall -C ${S}/bin/ -so liballegro_decode ${D}/${libdir}/
}

# These libraries shouldn't get installed in world builds unless something
# explicitly depends upon them.

EXCLUDE_FROM_WORLD = "1"

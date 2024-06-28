SUMMARY = "Firmware for VCU"
DESCRIPTION = "Firmware binaries provider for VCU"
LICENSE = "Proprietary"
LIC_FILES_CHKSUM = "file://LICENSE.md;md5=9bef8aa9d1eba8aca1b7dffdef500262"

PV .= "+git"

S  = "${WORKDIR}/git"

BRANCH ?= "master"
REPO ?= "git://github.com/Xilinx/vcu-firmware.git;protocol=https"
SRCREV = "787ef1b7b09b77631c8b825a35319f1857210ff1"

BRANCHARG = "${@['nobranch=1', 'branch=${BRANCH}'][d.getVar('BRANCH', True) != '']}"
SRC_URI   = "${REPO};${BRANCHARG}"

inherit features_check

REQUIRED_MACHINE_FEATURES = "vcu"

PACKAGE_ARCH = "${MACHINE_ARCH}"

do_compile[noexec] = "1"

do_install() {
    install -Dm 0644 ${S}/1.0.0/lib/firmware/al5d_b.fw ${D}${nonarch_base_libdir}/firmware/al5d_b.fw
    install -Dm 0644 ${S}/1.0.0/lib/firmware/al5d.fw ${D}${nonarch_base_libdir}/firmware/al5d.fw
    install -Dm 0644 ${S}/1.0.0/lib/firmware/al5e_b.fw ${D}${nonarch_base_libdir}/firmware/al5e_b.fw
    install -Dm 0644 ${S}/1.0.0/lib/firmware/al5e.fw ${D}${nonarch_base_libdir}/firmware/al5e.fw
}

# Inhibit warnings about files being stripped
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
INHIBIT_PACKAGE_STRIP = "1"
FILES:${PN} = "${nonarch_base_libdir}/firmware/*"

# These libraries shouldn't get installed in world builds unless something
# explicitly depends upon them.
EXCLUDE_FROM_WORLD = "1"

INSANE_SKIP:${PN} = "ldflags"
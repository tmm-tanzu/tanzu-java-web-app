# Get the currently used golang install path (in GOPATH/bin, unless GOBIN is set)
ifeq (,$(shell go env GOBIN))
GOBIN=$(shell go env GOPATH)/bin
else
GOBIN=$(shell go env GOBIN)
endif

# Tools
KCTRL ?= go run -modfile hack/kctrl/go.mod carvel.dev/kapp-controller/cli/cmd/kctrl

# Setting SHELL to bash allows bash commands to be executed by recipes.
# Options are set to exit when a recipe line exits non-zero or a piped command fails.
SHELL = /usr/bin/env bash -o pipefail
.SHELLFLAGS = -ec

##@ General

# The help target prints out all targets with their descriptions organized
# beneath their categories. The categories are represented by '##@' and the
# target descriptions by '##'. The awk commands is responsible for reading the
# entire set of makefiles included in this invocation, looking for lines of the
# file as xyz: ## something, and then pretty-format the target and help. Then,
# if there's a line with ##@ something, that gets pretty-printed as a category.
# More info on the usage of ANSI control characters for terminal formatting:
# https://en.wikipedia.org/wiki/ANSI_escape_code#SGR_parameters
# More info on the awk command:
# http://linuxcommand.org/lc3_adv_awk.php

.PHONY: help
help: ## Display this help
	@awk 'BEGIN {FS = ":.*##"; printf "\nUsage:\n  make \033[36m<target>\033[0m\n"} /^[a-zA-Z_0-9-]+:.*?##/ { printf "  \033[36m%-18s\033[0m %s\n", $$1, $$2 } /^##@/ { printf "\n\033[1m%s\033[0m\n", substr($$0, 5) } ' $(MAKEFILE_LIST)

.PHONY: clean
clean: ## Clean up build artifacts
	rm -rf carvel-artifacts

##@ Artifacts

.PHONY: manifests
manifests: ## Print Kubernetes manifests
	@cat <(echo "---") carvel-artifacts/package-repository.yml hack/kubernetes/*.yml

.PHONY: package
package: ## Release Carvel Package
	$(KCTRL) package release --yes

.PHONY: package-repository
package-repository: carvel-artifacts/pkgrepo-build.yml ## Release Carvel Package Repository
	$(KCTRL) package repository release --chdir carvel-artifacts --yes

carvel-artifacts/pkgrepo-build.yml: pkgrepo-build.yml
	@mkdir -p $(@D)
	@cp $< $@

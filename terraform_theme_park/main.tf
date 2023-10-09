provider "azurerm" {
  features {
    resource_group {
      prevent_deletion_if_contains_resources = false
    }
  }
  client_id       = var.client_id
  client_secret   = var.client_secret
  tenant_id       = var.tenant_id
  subscription_id = var.subscription_id
}

# Définition du groupe de ressources
resource "azurerm_resource_group" "theme_park_rg" {
  name     = "THEME_PARK_rg"
  location = "France Central"
}

# Configuration de la base de données CosmosDB en mode serverless
resource "azurerm_cosmosdb_account" "cosmosdb" {
  name                = "theme-park-cosmosdb"
  location            = azurerm_resource_group.theme_park_rg.location
  resource_group_name = azurerm_resource_group.theme_park_rg.name
  offer_type          = "Standard"
  kind                = "MongoDB"

  # Niveau de cohérence
  consistency_policy {
    consistency_level = "Session"
  }

  geo_location {
    location          = azurerm_resource_group.theme_park_rg.location
    failover_priority = 0
  }
}


# Configuration du cluster AKS pour le développement/test avec un profil prédéfini
resource "azurerm_kubernetes_cluster" "aks_cluster_dev" {
  name                = "theme-park-aks-dev"
  location            = azurerm_resource_group.theme_park_rg.location
  resource_group_name = azurerm_resource_group.theme_park_rg.name
  dns_prefix          = "theme-park-aks-dev"

  default_node_pool {
    name       = "default"
    node_count = 1  
    vm_size    = "Standard_DS2_v2"  # Taille de machine virtuelle "DS2_v2" pour le développement/test

  }

    service_principal {
    client_id       = var.client_id
    client_secret   = var.client_secret
  }

  tags = {
    environment = "dev/test"
  }
}
# Configuration du cluster de prod

resource "azurerm_kubernetes_cluster" "aks_cluster_prod" {
  name                = "theme-park-aks-prod"
  location            = azurerm_resource_group.theme_park_rg.location
  resource_group_name = azurerm_resource_group.theme_park_rg.name
  dns_prefix          = "theme-park-aks-prod"

  default_node_pool {
    name       = "default"
    node_count = 1 
    vm_size    = "Standard_DS2_v2"  # Taille de machine virtuelle "DS2_v2" pour le développement/test

  }

    service_principal {
    client_id       = var.client_id
    client_secret   = var.client_secret
  }



  tags = {
    environment = "prod"
  }
}

# Configuration du registre Azure Container Registry (ACR)
resource "azurerm_container_registry" "acr" {
  name = "themeParkAcr"  
  resource_group_name = azurerm_resource_group.theme_park_rg.name
  location = azurerm_resource_group.theme_park_rg.location
  sku = "Basic"
  admin_enabled = true
}